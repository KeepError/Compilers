package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.Expression;
import Token.Token;
import Token.TokenType;

public class WhileStatement extends Statement {
    private final Expression test;
    private final BlockStatement body;

    public WhileStatement(Expression test, BlockStatement body) {
        this.test = test;
        this.body = body;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("while");
    }

    public static ParseResult<WhileStatement> parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);
        
        ParseResult<Expression> test = Expression.parse(tokens, tokenIndex);
        tokenIndex += test.tokensParsed();
        token = tokens.get(tokenIndex);

        if (!token.isToken("loop"))
            throw new UnexpectedTokenError(token);
        ParseResult<BlockStatement> body = BlockStatement.parse(tokens, ++tokenIndex);
        tokenIndex += body.tokensParsed();
        
        WhileStatement loopStatement = new WhileStatement(test.statement(), body.statement());
        return new ParseResult(loopStatement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "WhileStatement{test="+ test +",body=[" + body + "]";
    }

    public BlockStatement getBody() {
        return body;
    }
    
    public Expression getTest() {
        return test;
    }
}
