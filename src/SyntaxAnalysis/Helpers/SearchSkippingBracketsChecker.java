package SyntaxAnalysis.Helpers;

import LexicalAnalysis.Tokens.Token;

public interface SearchSkippingBracketsChecker {
    boolean check(Token token);
}
