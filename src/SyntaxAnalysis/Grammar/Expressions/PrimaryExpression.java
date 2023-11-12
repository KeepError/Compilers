package SyntaxAnalysis.Grammar.Expressions;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Literals.Literal;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class PrimaryExpression extends Expression {
    private final Literal literal;

    public PrimaryExpression(int startToken, int tokensCount, Literal literal) {
        super(startToken, tokensCount);
        this.literal = literal;
    }

    public static PrimaryExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Literal literal = Literal.findNext(tokens, startToken);
        if (literal == null) return null;
        return new PrimaryExpression(startToken, literal.getTokensCount(), literal);
    }

    public static PrimaryExpression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        PrimaryExpression primaryExpression = findNext(tokens, startToken);
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
