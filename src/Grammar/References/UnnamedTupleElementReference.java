package Grammar.References;

import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.IntegerToken;
import Tokens.SeparatorToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class UnnamedTupleElementReference extends Reference {
    Reference object;
    Integer tupleElementNumber;

    public UnnamedTupleElementReference(int startToken, int tokensCount, Reference object, Integer tupleElementNumber) {
        super(startToken, tokensCount);
        this.object = object;
        this.tupleElementNumber = tupleElementNumber;
    }

    public static UnnamedTupleElementReference findNext(List<Token> tokens, int startToken, Reference object) throws SyntaxError {
        int currentToken = startToken + object.getTokensCount();
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("."))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        token = tokens.get(currentToken);
        if (!(token instanceof IntegerToken)) {
            return null;
        }
        Integer number = ((IntegerToken) token).getIntegerValue();
        currentToken++;
        return new UnnamedTupleElementReference(startToken, currentToken - startToken, object, number);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        object.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("tupleElementNumber", tupleElementNumber);
        return fields;
    }
}
