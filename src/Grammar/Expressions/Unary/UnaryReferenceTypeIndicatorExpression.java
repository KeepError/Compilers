package Grammar.Expressions.Unary;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.References.Reference;
import Grammar.SyntaxError;
import Grammar.TypeIndicators.TypeIndicator;

import java.util.List;
import java.util.Map;

public class UnaryReferenceTypeIndicatorExpression extends UnaryExpression {
    private final Reference reference;
    private final TypeIndicator typeIndicator;

    public UnaryReferenceTypeIndicatorExpression(int startToken, int tokensCount, Reference reference, TypeIndicator typeIndicator) {
        super(startToken, tokensCount);
        this.reference = reference;
        this.typeIndicator = typeIndicator;
    }

    public static UnaryReferenceTypeIndicatorExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Reference reference = Reference.findNext(tokens, currentToken);
        if (reference == null) return null;
        currentToken += reference.getTokensCount();
        if (currentToken > tokens.size()) return null;

        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("is"))) {
            return null;
        }
        currentToken++;
        if (currentToken > tokens.size()) return null;

        TypeIndicator typeIndicator = TypeIndicator.findNext(tokens, currentToken);
        if (typeIndicator == null) return null;
        currentToken += typeIndicator.getTokensCount();

        return new UnaryReferenceTypeIndicatorExpression(startToken, currentToken - startToken, reference, typeIndicator);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        fields.put("typeIndicator", typeIndicator);
        return fields;
    }
}
