package Statement;

import java.util.List;

import Statement.Expression.Expression;
import Token.Token;
import Token.TokenType;

public class IfStatement extends Statement {
    private final Expression test;
    private final BlockStatement body;

    public IfStatement(Expression test, BlockStatement body) {
        this.test = test;
        this.body = body;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("if");
    }

    public static ParseResult<ForStatement> parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);
        
        ParseResult<Expression> test = Expression.parse(tokens, tokenIndex);
        tokenIndex += test.tokensParsed();
        token = tokens.get(tokenIndex);

        if (!token.isToken("then"))
            throw new UnexpectedTokenError(token);
        ParseResult<BlockStatement> body = BlockStatement.parse(tokens, ++tokenIndex);
        tokenIndex += body.tokensParsed();

        // TODO Parsing else statement
        
        IfStatement ifStatement = new IfStatement(test.statement(), body.statement());
        return new ParseResult(ifStatement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "IfStatement{test="+ test +",body=[" + body + "]";
    }
}
