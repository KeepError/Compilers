package Grammar;

import Tokens.Token;

public class SyntaxError extends Exception {
    public SyntaxError(Token token, String message) {
        super(String.format("Syntax Error at line %s, column %s: %s", token.getLine() + 1, token.getColumn() + 1, message));
    }
}
