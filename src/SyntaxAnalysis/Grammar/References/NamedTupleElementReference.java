package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.IdentifierToken;
import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class NamedTupleElementReference extends Reference {
    Reference object;
    String tupleElementIdentifier;

    public NamedTupleElementReference(int startToken, int tokensCount, Reference object, String tupleElementIdentifier) {
        super(startToken, tokensCount);
        this.object = object;
        this.tupleElementIdentifier = tupleElementIdentifier;
    }

    public static NamedTupleElementReference findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int endToken = tokens.size() - 1;
        do {
            NamedTupleElementReference namedTupleElementReference = findInRange(tokens, startToken, endToken);
            if (namedTupleElementReference != null) {
                return namedTupleElementReference;
            }
            endToken--;
        } while (endToken >= startToken);
        return null;
    }

    public static NamedTupleElementReference findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        Token token = tokens.get(endToken);
        if (!(token instanceof IdentifierToken)) {
            return null;
        }
        String identifier = ((IdentifierToken) token).getIdentifier();
        if ((endToken - 1) < startToken || !(tokens.get(endToken - 1) instanceof SeparatorToken && ((SeparatorToken) tokens.get(endToken - 1)).getSeparator().equals("."))) {
            return null;
        }
        Reference object = Reference.findInRange(tokens, startToken, endToken - 2);
        if (object == null) {
            return null;
        }
        return new NamedTupleElementReference(startToken, endToken - startToken + 1, object, identifier);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("tupleElementIdentifier", tupleElementIdentifier);
        return fields;
    }
}
