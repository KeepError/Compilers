package SyntaxAnalysis.Grammar.Expressions;

import LexicalAnalysis.Tokens.IntegerToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.References.Reference;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.sql.Ref;
import java.util.List;
import java.util.Map;

public class UnaryExpression extends Expression {
    private final Reference reference;

    public UnaryExpression(int startToken, int tokensCount, Reference reference) {
        super(startToken, tokensCount);
        this.reference = reference;
    }

    public static UnaryExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Reference reference = Reference.findNext(tokens, startToken);
        if (reference == null) return null;
        return new UnaryExpression(startToken, reference.getTokensCount(), reference);
    }

    public static UnaryExpression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        UnaryExpression unaryExpression = findNext(tokens, startToken);
        if (unaryExpression == null || unaryExpression.getTokensCount() != endToken - startToken + 1) return null;
        return unaryExpression;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        return fields;
    }
}
