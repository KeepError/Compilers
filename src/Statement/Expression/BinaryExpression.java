package Statement.Expression;

import Token.Token;
import Token.TokenType;
import Statement.ParseResult;
import Statement.UnexpectedTokenError;

import java.util.List;

class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;
    private final String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        int nextTokenIndex = startTokenIndex + 1;
        Token nextToken = tokens.get(nextTokenIndex);

        return nextToken.isType(TokenType.OPERATOR);
    }

    public static ParseResult<BinaryExpression> parse(List<Token> tokens, int startTokenIndex) {
        int tokenIndex = startTokenIndex;
        Token token = tokens.get(tokenIndex);
        ParseResult<Expression> left = Expression.parseWithConfig(tokens, tokenIndex, false);
        tokenIndex++;

        token = tokens.get(tokenIndex);
        if (!token.isType(TokenType.OPERATOR)) 
            throw new UnexpectedTokenError(token);
        String operator = token.getToken();
        tokenIndex++;

        token = tokens.get(tokenIndex);
        ParseResult<Expression> right = Expression.parse(tokens, tokenIndex);
        tokenIndex = tokenIndex + right.tokensParsed();

        BinaryExpression binaryExpression = new BinaryExpression(left.statement(), right.statement(), operator);

        return new ParseResult<BinaryExpression>(binaryExpression, tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "BinaryExpression{left=" + left + ",right=" + right + ",operator=" + operator + "}";
    }
}