package SyntaxAnalysis.Grammar.Expressions.Unary;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.References.Reference;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class UnaryReferenceExpression extends UnaryExpression {
    private final Reference reference;

    public UnaryReferenceExpression(int startToken, int tokensCount, Reference reference) {
        super(startToken, tokensCount);
        this.reference = reference;
    }

    public static UnaryReferenceExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Reference reference = Reference.findNext(tokens, startToken);
        if (reference == null) return null;
        return new UnaryReferenceExpression(startToken, reference.getTokensCount(), reference);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        return fields;
    }
}
