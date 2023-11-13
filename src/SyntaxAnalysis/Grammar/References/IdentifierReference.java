package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.IdentifierToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class IdentifierReference extends Reference {
    String identifier;

    public IdentifierReference(int startToken, int tokensCount, String identifier) {
        super(startToken, tokensCount);
        this.identifier = identifier;
    }

    public static IdentifierReference findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof IdentifierToken)) {
            return null;
        }
        String identifier = ((IdentifierToken) token).getIdentifier();
        return new IdentifierReference(startToken, 1, identifier);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", identifier);
        return fields;
    }
}

