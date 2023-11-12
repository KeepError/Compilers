package SyntaxAnalysis.Grammar;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.SyntaxAnalyserError;

public class SyntaxError extends SyntaxAnalyserError {
    public SyntaxError(Token token, String message) {
        super(String.format("Syntax Error at line %s, column %s: %s", token.getLine(), token.getColumn(), message));
    }
}
