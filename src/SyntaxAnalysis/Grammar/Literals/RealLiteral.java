package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.RealToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

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

    public static RealLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        RealLiteral realLiteral = findNext(tokens, startToken);
        if (realLiteral == null || realLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return realLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("realValue", realValue);
        return fields;
    }
}
