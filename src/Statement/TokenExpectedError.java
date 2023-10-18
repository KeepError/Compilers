package Statement;

import Token.Token;

public class TokenExpectedError extends TokenError {
    public TokenExpectedError(Token lastToken) {
        super("Token.Token expected after: " + lastToken);
    }
}
