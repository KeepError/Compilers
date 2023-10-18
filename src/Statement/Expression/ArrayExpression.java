package Statement.Expression;

import java.util.ArrayList;
import java.util.List;

import Statement.ParseResult;
import Statement.UnexpectedTokenError;
import Token.Token;
import Token.TokenType;

public class ArrayExpression extends Expression {
    private final List<Expression> expressions;

    public ArrayExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.SEPARATOR) && token.isToken("[");
    }

    static public ParseResult<ArrayExpression> parse(List<Token> tokens, int startTokenIndex) {
        int tokenIndex = startTokenIndex + 1;
        Token token = tokens.get(tokenIndex);
        List<Expression> expressions = new ArrayList<>();
        boolean previousTokenIsSeparator = true;
        while(!token.isToken("]")) {
            if (!previousTokenIsSeparator && token.isToken(",")) {
                previousTokenIsSeparator = true;
                tokenIndex++;
            } else if (previousTokenIsSeparator) {
                previousTokenIsSeparator = false;
                ParseResult<Expression> parseResult = Expression.parse(tokens, tokenIndex);
                tokenIndex = parseResult.tokensParsed() + tokenIndex;
                expressions.add((Expression) parseResult.statement());
            } else {
                throw new UnexpectedTokenError(token);
            }
            
            token = tokens.get(tokenIndex);
        }
        tokenIndex++;
        System.out.println(tokenIndex);
        return new ParseResult<ArrayExpression>(new ArrayExpression(expressions), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "ArrayExpression{expressions=" + expressions + "}";
    }
}
