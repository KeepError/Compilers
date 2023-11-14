package Grammar.Literals;

import Tokens.IdentifierToken;
import Tokens.OperatorToken;
import Tokens.Token;
import Grammar.Expressions.Expression;
import Grammar.Grammar;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class TupleElement extends Grammar {
    String identifier;
    Expression value;

    public TupleElement(int startToken, int tokensCount, String identifier, Expression value) {
        super(startToken, tokensCount);
        this.identifier = identifier;
        this.value = value;
    }

    public static String findIdentifier(List<Token> tokens, int startToken) {
        Token idToken = tokens.get(startToken);
        if (!(idToken instanceof IdentifierToken)) return null;
        if (startToken + 1 >= tokens.size()) return null;
        Token opToken = tokens.get(startToken + 1);
        if (!(opToken instanceof OperatorToken && ((OperatorToken) opToken).getOperator().equals(":="))) return null;
        return ((IdentifierToken) idToken).getIdentifier();
    }

    public static TupleElement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        String identifier = findIdentifier(tokens, startToken);
        int currentToken = startToken;
        if (identifier != null) currentToken += 2;
        if (currentToken >= tokens.size()) return null;
        Expression value = Expression.findNext(tokens, currentToken);
        if (value == null) return null;
        currentToken += value.getTokensCount();
        return new TupleElement(startToken, currentToken - startToken, identifier, value);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", identifier);
        fields.put("value", value);
        return fields;
    }
}
