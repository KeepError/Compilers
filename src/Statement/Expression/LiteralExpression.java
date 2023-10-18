package Statement.Expression;

import Statement.ParseResult;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class LiteralExpression extends Expression {
    private final String value;

    public LiteralExpression(String value) {
        this.value = value;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.STRING) || token.isType(TokenType.DIGIT) || token.isType(TokenType.DOT)
                || (token.isType(TokenType.KEYWORD) && (token.isToken("true") || token.isToken("false")));
    }

    static public ParseResult<LiteralExpression> parse(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return new ParseResult<LiteralExpression>(new LiteralExpression(token.getToken()), 1);
    }

    @Override
    public String toString() {
        return "Literal{" + value + "}";
    }
}
