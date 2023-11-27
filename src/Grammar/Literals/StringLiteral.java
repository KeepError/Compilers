package Grammar.Literals;

import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.Values.StringValue;
import Symbols.Values.Value;
import Tokens.StringToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class StringLiteral extends Literal {
    String stringValue;

    public StringLiteral(int startToken, int tokensCount, String stringValue) {
        super(startToken, tokensCount);
        this.stringValue = stringValue;
    }

    public static StringLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof StringToken) {
            return new StringLiteral(startToken, 1, ((StringToken) token).getStringValue());
        }
        return null;
    }

    @Override
    public Value getValue(SymbolTable symbolTable) {
        return new StringValue(stringValue);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("stringValue", stringValue);
        return fields;
    }
}
