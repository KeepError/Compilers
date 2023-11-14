package Grammar.References;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.IdentifierToken;
import Tokens.SeparatorToken;
import Tokens.Token;
import Grammar.SyntaxError;

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

    public static NamedTupleElementReference findNext(List<Token> tokens, int startToken, Reference object) throws SyntaxError {
        int currentToken = startToken + object.getTokensCount();
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("."))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        token = tokens.get(currentToken);
        if (!(token instanceof IdentifierToken)) {
            return null;
        }
        String identifier = ((IdentifierToken) token).getIdentifier();
        currentToken++;
        return new NamedTupleElementReference(startToken, currentToken - startToken, object, identifier);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        object.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("tupleElementIdentifier", tupleElementIdentifier);
        return fields;
    }
}
