package SyntaxAnalysis.Helpers;

import LexicalAnalysis.Tokens.Token;

import java.util.List;

public interface SearchSkippingBracketsChecker {
    boolean check(Token token);
}
