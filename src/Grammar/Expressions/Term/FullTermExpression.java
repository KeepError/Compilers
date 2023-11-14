package Grammar.Expressions.Term;

import Tokens.OperatorToken;
import Tokens.Token;
import Grammar.Expressions.Unary.UnaryExpression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class FullTermExpression extends TermExpression {
    private final TermExpression left;
    private final String operator;
    private final UnaryExpression right;

    public FullTermExpression(int startToken, int tokensCount, TermExpression left, String operator, UnaryExpression right) {
        super(startToken, tokensCount);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public static FullTermExpression findNext(List<Token> tokens, int startToken, TermExpression left) throws SyntaxError {
        int currentToken = startToken + left.getTokensCount();
        if (currentToken >= tokens.size()) return null;

        Token token = tokens.get(currentToken);
        if (!(token instanceof OperatorToken)) return null;
        String operator = ((OperatorToken) token).getOperator();
        if (!(operator.equals("*") || operator.equals("/"))) return null;
        currentToken++;
        if (currentToken > tokens.size()) return null;

        UnaryExpression right = UnaryExpression.findNext(tokens, currentToken);
        if (right == null) return null;
        currentToken += right.getTokensCount();

        return new FullTermExpression(startToken, currentToken - startToken, left, operator, right);
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
