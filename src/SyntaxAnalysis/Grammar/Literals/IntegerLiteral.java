package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.IntegerToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class IntegerLiteral extends Literal {
    int integerValue;

    public IntegerLiteral(int startToken, int tokensCount, int integerValue) {
        super(startToken, tokensCount);
        this.integerValue = integerValue;
    }

    public static IntegerLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof IntegerToken) {
            return new IntegerLiteral(startToken, 1, ((IntegerToken) token).getIntegerValue());
        }
        return null;
    }

    public static IntegerLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        IntegerLiteral integerLiteral = findNext(tokens, startToken);
        if (integerLiteral == null || integerLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return integerLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("integerValue", integerValue);
        return fields;
    }
}
