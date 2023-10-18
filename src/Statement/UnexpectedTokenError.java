package Statement;

import Token.Token;

public class UnexpectedTokenError extends TokenError {
    public UnexpectedTokenError(Token token) {
        super("Unexpected token: " + token);
    }
}
