package Grammar.References;

import Tokens.SeparatorToken;
import Tokens.Token;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class ArrayElementReference extends Reference {
    Reference object;
    Expression arrayElement;

    public ArrayElementReference(int startToken, int tokensCount, Reference object, Expression arrayElement) {
        super(startToken, tokensCount);
        this.object = object;
        this.arrayElement = arrayElement;
    }

    public static ArrayElementReference findNext(List<Token> tokens, int startToken, Reference object) throws SyntaxError {
        int currentToken = startToken + object.getTokensCount();
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("["))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        Expression expression = Expression.findNext(tokens, currentToken);
        if (expression == null) {
            return null;
        }
        currentToken += expression.getTokensCount();
        token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("]"))) {
            return null;
        }
        currentToken++;
        return new ArrayElementReference(startToken, currentToken - startToken, object, expression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("arrayElement", arrayElement);
        return fields;
    }
}
