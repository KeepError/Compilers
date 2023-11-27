package Grammar.References;

import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolValue;
import Symbols.SymbolsError;
import Tokens.IdentifierToken;
import Tokens.Token;

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
    public SymbolValue getSymbolValue(SymbolTable symbolTable) throws SymbolsError {
        return symbolTable.getCurrentScope().getSymbolValue(identifier);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.assignGrammar(this);
        symbolTable.getCurrentScope().reference(identifier);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", identifier);
        return fields;
    }
}

