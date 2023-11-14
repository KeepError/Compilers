package Grammar.TypeIndicators;

import Tokens.SeparatorToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.List;

public class ArrayTypeIndicator extends TypeIndicator {
    public ArrayTypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static ArrayTypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof SeparatorToken)) return null;
        String separator = ((SeparatorToken) token).getSeparator();
        if (!(separator.equals("["))) return null;
        if (startToken + 1 >= tokens.size()) return null;
        token = tokens.get(startToken + 1);
        if (!(token instanceof SeparatorToken)) return null;
        separator = ((SeparatorToken) token).getSeparator();
        if (!(separator.equals("]"))) return null;
        return new ArrayTypeIndicator(startToken, 2);
    }
}
