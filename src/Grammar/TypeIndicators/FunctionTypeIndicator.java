package Grammar.TypeIndicators;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.List;

public class FunctionTypeIndicator extends TypeIndicator {
    public FunctionTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static FunctionTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("func"))) return null;
        return new FunctionTypeIndicator(startToken, 1);
    }
}
