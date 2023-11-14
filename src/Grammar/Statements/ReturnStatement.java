package Grammar.Statements;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class ReturnStatement extends Statement {
    Expression expression;

    public ReturnStatement(int startToken, int tokensCount, Expression expression) {
        super(startToken, tokensCount);
        this.expression = expression;
    }

    public static ReturnStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("return"))) {
            return null;
        }
        Expression expression = null;
        if (startToken + 1 < tokens.size()) {
            expression = Expression.findNext(tokens, startToken + 1);
        }
        int tokensCount = 1;
        if (expression != null) {
            tokensCount += expression.getTokensCount();
        }
        return new ReturnStatement(startToken, tokensCount, expression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("expression", expression);
        return fields;
    }
}
