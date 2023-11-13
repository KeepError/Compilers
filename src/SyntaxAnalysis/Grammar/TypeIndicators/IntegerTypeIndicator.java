package SyntaxAnalysis.Grammar.TypeIndicators;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public class IntegerTypeIndicator extends TypeIndicator {
    public IntegerTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static IntegerTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) return null;
        String keyword = ((KeywordToken) token).getKeyword();
        if (!(keyword.equals("int"))) return null;
        return new IntegerTypeIndicator(startToken, 1);
    }
}
