package SyntaxAnalysis.Helpers;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;

import java.util.List;

public class Helpers {
    public static Integer searchSkippingBrackets(List<Token> tokens, int end, SearchSkippingBracketsChecker checker) {
        int bracketsCount = 0;
        int currentToken = end;
        while (currentToken >= 0) {
            Token token = tokens.get(currentToken);
            if (bracketsCount == 0 && checker.check(token)) {
                return currentToken;
            }
            if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(")")) {
                bracketsCount++;
            } else if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("]")) {
                bracketsCount++;
            } else if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("(")) {
                bracketsCount--;
            } else if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("[")) {
                bracketsCount--;
            }
            if (bracketsCount < 0) return null;
            currentToken--;
        }
        return null;
    }
}
