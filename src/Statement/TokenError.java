package Statement;

public abstract class TokenError extends RuntimeException {
    public TokenError(String message) {
        super(message);
    }
}
