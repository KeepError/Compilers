package Grammar.TypeIndicators;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.List;

public class BooleanTypeIndicator extends TypeIndicator {
    public BooleanTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static BooleanTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("bool"))) return null;
        return new BooleanTypeIndicator(startToken, 1);
    }
}
