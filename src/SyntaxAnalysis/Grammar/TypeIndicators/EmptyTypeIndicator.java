package SyntaxAnalysis.Grammar.TypeIndicators;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public class EmptyTypeIndicator extends TypeIndicator {
    public EmptyTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static EmptyTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("empty"))) return null;
        return new EmptyTypeIndicator(startToken, 1);
    }
}
