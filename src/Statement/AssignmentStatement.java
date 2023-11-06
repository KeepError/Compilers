package Statement;

import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class AssignmentStatement extends Statement {
    private final IdentifierExpression identifier;
    private final Expression expression;

    public AssignmentStatement(IdentifierExpression identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public static boolean isStart(List<Token> tokens, int startTokenIndex) {
        try {
            Token nextToken = tokens.get(startTokenIndex + 1);
            return IdentifierExpression.isStart(tokens, startTokenIndex) && nextToken.isType(TokenType.OPERATOR) && nextToken.isToken(":=");
        } catch (Exception e) {
            return false;
        }
    }

    public static ParseResult parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start;
        Token token = tokens.get(tokenIndex);
        if (!IdentifierExpression.isStart(tokens, tokenIndex))
            throw new UnexpectedTokenError(token);
        ParseResult identifier = IdentifierExpression.parse(tokens, tokenIndex);
        tokenIndex += identifier.tokensParsed();
        token = tokens.get(tokenIndex);
        if (!token.isType(TokenType.OPERATOR) || !token.isToken(":="))
            throw new UnexpectedTokenError(token);
        ParseResult expression = Expression.parse(tokens, tokenIndex + 1);
        tokenIndex += expression.tokensParsed();
        AssignmentStatement assignmentStatement = new AssignmentStatement((IdentifierExpression) identifier.statement(), (Expression) expression.statement());
        tokenIndex++;
        if (!tokens.get(tokenIndex).isToken(";"))
            throw new UnexpectedTokenError(tokens.get(tokenIndex));
        tokenIndex++;
        return new ParseResult(assignmentStatement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "AssignmentStatement{identifier='" + identifier + "', expression=" + expression + "}";
    }

    public Expression getExpression() {
        return this.expression;
    }

    public IdentifierExpression getIdentifier() {
        return this.identifier;
    }
}
