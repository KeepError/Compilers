package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import Token.Token;
import Token.TokenType;

public class ForStatement extends Statement {
    private final IdentifierExpression identifier;
    private final Expression ofExpression; // FIXME Replace it with test
    private final BlockStatement body;

    public ForStatement(IdentifierExpression identifier, Expression ofExpression, BlockStatement body) {
        this.identifier = identifier;
        this.ofExpression = ofExpression;
        this.body = body;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("for");
    }

    public static ParseResult<ForStatement> parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);
        
        ParseResult<IdentifierExpression> identifier = IdentifierExpression.parse(tokens, tokenIndex);
        tokenIndex += identifier.tokensParsed();
        token = tokens.get(tokenIndex);

        if (!token.isToken("in"))
            throw new UnexpectedTokenError(token);
        tokenIndex += 1;
        token = tokens.get(tokenIndex);

        ParseResult<Expression> ofExpression = Expression.parse(tokens, tokenIndex);
        tokenIndex += 1;
        token = tokens.get(tokenIndex);

        if (!token.isToken("loop"))
            throw new UnexpectedTokenError(token);
        ParseResult<BlockStatement> body = BlockStatement.parse(tokens, ++tokenIndex);
        tokenIndex += body.tokensParsed();
        
        ForStatement forStatement = new ForStatement(identifier.statement(), ofExpression.statement(), body.statement());
        return new ParseResult(forStatement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "ForStatement{identifier="+ identifier +",typeIndicator=" + ofExpression + ",body=[" + body + "]";
    }
}
