package Grammar.Literals;

import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.Values.RealValue;
import Symbols.Values.Value;
import Tokens.RealToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class RealLiteral extends Literal {
    float realValue;

    public RealLiteral(int startToken, int tokensCount, float realValue) {
        super(startToken, tokensCount);
        this.realValue = realValue;
    }

    public static RealLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof RealToken) {
            return new RealLiteral(startToken, 1, ((RealToken) token).getRealValue());
        }
        return null;
    }

    @Override
    public Value getValue(SymbolTable symbolTable) {
        return new RealValue(realValue);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("realValue", realValue);
        return fields;
    }
}
