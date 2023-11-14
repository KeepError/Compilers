package Grammar.TypeIndicators;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.List;

public class RealTypeIndicator extends TypeIndicator {
    public RealTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static RealTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("real"))) return null;
        return new RealTypeIndicator(startToken, 1);
    }
}
