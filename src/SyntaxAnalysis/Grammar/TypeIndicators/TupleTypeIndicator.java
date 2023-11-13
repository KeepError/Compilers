package SyntaxAnalysis.Grammar.TypeIndicators;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public class TupleTypeIndicator extends TypeIndicator {
    public TupleTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static TupleTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof SeparatorToken)) return null;
        String separator = ((SeparatorToken) token).getSeparator();
        if (!(separator.equals("{"))) return null;
        if (startToken + 1 >= tokens.size()) return null;
        token = tokens.get(startToken + 1);
        if (!(token instanceof SeparatorToken)) return null;
        separator = ((SeparatorToken) token).getSeparator();
        if (!(separator.equals("}"))) return null;
        return new TupleTypeIndicator(startToken, 2);
    }
}
