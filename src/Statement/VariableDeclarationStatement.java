package Statement;

import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class VariableDeclarationStatement extends Statement {
    private final IdentifierExpression identifier;
    private final Expression expression;

    public VariableDeclarationStatement(IdentifierExpression identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public static boolean isStart(List<Token> tokens, int start) {
        Token token = tokens.get(start);
        return token.isType(TokenType.KEYWORD) && token.isToken("var");
    }

    public static ParseResult parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
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
        VariableDeclarationStatement statement = new VariableDeclarationStatement((IdentifierExpression) identifier.statement(), (Expression) expression.statement());
        tokenIndex++;
        if (!tokens.get(tokenIndex).isToken(";"))
            throw new UnexpectedTokenError(tokens.get(tokenIndex));
        tokenIndex++;
        return new ParseResult(statement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "VariableDeclaration{identifier='" + identifier + "', expression=" + expression + "}";
    }
}
