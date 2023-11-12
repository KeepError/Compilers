package SyntaxAnalysis.Grammar.Expressions.Primary;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Literals.Literal;
import SyntaxAnalysis.Grammar.SyntaxError;

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

    public static PrimaryLiteralExpression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        PrimaryLiteralExpression primaryExpression = findNext(tokens, startToken);
        if (primaryExpression == null || primaryExpression.getTokensCount() != endToken - startToken + 1) return null;
        return primaryExpression;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("literal", literal);
        return fields;
    }
}
