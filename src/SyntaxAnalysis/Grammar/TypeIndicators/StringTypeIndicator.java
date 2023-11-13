package SyntaxAnalysis.Grammar.TypeIndicators;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public class StringTypeIndicator extends TypeIndicator {
    public StringTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static StringTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("string"))) return null;
        return new StringTypeIndicator(startToken, 1);
    }
}
