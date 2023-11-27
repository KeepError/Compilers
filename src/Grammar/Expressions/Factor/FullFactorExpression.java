package Grammar.Expressions.Factor;

import Grammar.Expressions.Term.TermExpression;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.OperatorToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class FullFactorExpression extends FactorExpression {
    private final FactorExpression left;
    private final String operator;
    private final TermExpression right;

    public FullFactorExpression(int startToken, int tokensCount, FactorExpression left, String operator, TermExpression right) {
        super(startToken, tokensCount);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public static FullFactorExpression findNext(List<Token> tokens, int startToken, FactorExpression left) throws SyntaxError {
        int currentToken = startToken + left.getTokensCount();
        if (currentToken >= tokens.size()) return null;

        Token token = tokens.get(currentToken);
        if (!(token instanceof OperatorToken)) return null;
        String operator = ((OperatorToken) token).getOperator();
        if (!(operator.equals("+") || operator.equals("-"))) return null;
        currentToken++;
        if (currentToken > tokens.size()) return null;

        TermExpression right = TermExpression.findNext(tokens, currentToken);
        if (right == null) return null;
        currentToken += right.getTokensCount();

        return new FullFactorExpression(startToken, currentToken - startToken, left, operator, right);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        Value leftValue = left.evaluate(symbolTable);
        Value rightValue = right.evaluate(symbolTable);
        return switch (operator) {
            case "+" -> leftValue.add(rightValue);
            case "-" -> leftValue.subtract(rightValue);
            default -> throw new SymbolsError("Unknown operator: " + operator);
        };
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        left.analyse(symbolTable);
        right.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("left", left);
        fields.put("operator", operator);
        fields.put("right", right);
        return fields;
    }
}
