package Grammar.Literals;

import Symbols.SymbolTable;
import Symbols.Values.BooleanValue;
import Symbols.Values.Value;
import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class BooleanLiteral extends Literal {
    boolean booleanValue;

    public BooleanLiteral(int startToken, int tokensCount, boolean booleanValue) {
        super(startToken, tokensCount);
        this.booleanValue = booleanValue;
    }

    public static BooleanLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof KeywordToken) {
            String keyword = ((KeywordToken) token).getKeyword();
            if (keyword.equals("true")) {
                return new BooleanLiteral(startToken, 1, true);
            } else if (keyword.equals("false")) {
                return new BooleanLiteral(startToken, 1, false);
            }
        }
        return null;
    }

    @Override
    public Value getValue(SymbolTable symbolTable) {
        return new BooleanValue(booleanValue);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("booleanValue", booleanValue);
        return fields;
    }
}
