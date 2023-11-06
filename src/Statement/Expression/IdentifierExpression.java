package Statement.Expression;

import Statement.ParseResult;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class IdentifierExpression extends Expression {
    private final String name;

    public IdentifierExpression(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.IDENTIFIER);
    }

    static public ParseResult<IdentifierExpression> parse(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return new ParseResult<IdentifierExpression>(new IdentifierExpression(token.getToken()), 1);
    }

    @Override
    public String toString() {
        return "Identifier{" + name + "}";
    }
}
