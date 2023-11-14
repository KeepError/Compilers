package Grammar.Expressions.Primary;

import Tokens.Token;
import Grammar.Literals.Literal;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class PrimaryLiteralExpression extends PrimaryExpression {
    private final Literal literal;

    public PrimaryLiteralExpression(int startToken, int tokensCount, Literal literal) {
        super(startToken, tokensCount);
        this.literal = literal;
    }

    public static PrimaryLiteralExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Literal literal = Literal.findNext(tokens, startToken);
        if (literal == null) return null;
        return new PrimaryLiteralExpression(startToken, literal.getTokensCount(), literal);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("literal", literal);
        return fields;
    }
}
