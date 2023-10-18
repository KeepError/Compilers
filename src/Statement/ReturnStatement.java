package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.Expression;
import Token.Token;
import Token.TokenType;

public class ReturnStatement extends Expression {
    private final Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("return");
    }

    static public ParseResult<ReturnStatement> parse(List<Token> tokens, int startTokenIndex) {
        int tokenIndex = startTokenIndex + 1;

        ParseResult parseResult = Expression.parse(tokens, tokenIndex);
        tokenIndex += parseResult.tokensParsed();
        Expression expression = (Expression) parseResult.statement();
        Token token = tokens.get(tokenIndex);

        if (!(token.isType(TokenType.SEPARATOR) && tokens.get(tokenIndex).isToken(";"))) {
            throw new UnexpectedTokenError(tokens.get(tokenIndex));
        }
        tokenIndex++;

        return new ParseResult<ReturnStatement>(new ReturnStatement(expression), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "ReturnStatement{expression=" + expression + "}";
    }
}