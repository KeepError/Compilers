package Statement;

import java.util.List;

import Statement.Expression.Expression;
import Token.Token;
import Token.TokenType;

public class ExpressionStatement extends Statement {
    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public static ParseResult<ExpressionStatement> parse(List<Token> tokens, int start) throws TokenError {
        ParseResult parseResult = Expression.parse(tokens, start);
        int tokenIndex = start + parseResult.tokensParsed();
        if (tokens.get(tokenIndex).isType(TokenType.SEPARATOR) && tokens.get(tokenIndex).isToken(";"))
            tokenIndex++;
        else
            throw new UnexpectedTokenError(tokens.get(tokenIndex));
        return new ParseResult(new ExpressionStatement((Expression) parseResult.statement()), tokenIndex - start);
    }

    @Override
    public String toString() {
        return "ExpressionStatement{expression=" + expression + "}";
    }
}
