package SyntaxAnalysis.Grammar.Expressions.Factor;

import LexicalAnalysis.Tokens.OperatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Term.TermExpression;
import SyntaxAnalysis.Grammar.SyntaxError;

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
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("left", left);
        fields.put("operator", operator);
        fields.put("right", right);
        return fields;
    }
}
