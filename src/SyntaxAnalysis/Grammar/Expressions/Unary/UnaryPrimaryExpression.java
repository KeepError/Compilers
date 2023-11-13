package SyntaxAnalysis.Grammar.Expressions.Unary;

import LexicalAnalysis.Tokens.OperatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Primary.PrimaryExpression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class UnaryPrimaryExpression extends UnaryExpression {
    private final String operator;
    private final PrimaryExpression primary;

    public UnaryPrimaryExpression(int startToken, int tokensCount, String operator, PrimaryExpression primary) {
        super(startToken, tokensCount);
        this.operator = operator;
        this.primary = primary;
    }

    public static UnaryPrimaryExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        String expressionOperator = null;
        Token token = tokens.get(currentToken);
        if (token instanceof OperatorToken) {
            String operator = ((OperatorToken) token).getOperator();
            if (operator.equals("+") || operator.equals("-") || operator.equals("not")) {
                expressionOperator = ((OperatorToken) token).getOperator();
                currentToken++;
            }
        }
        PrimaryExpression primary = PrimaryExpression.findNext(tokens, currentToken);
        if (primary == null) return null;
        currentToken += primary.getTokensCount();
        return new UnaryPrimaryExpression(startToken, currentToken - startToken, expressionOperator, primary);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("operator", operator);
        fields.put("primary", primary);
        return fields;
    }
}
