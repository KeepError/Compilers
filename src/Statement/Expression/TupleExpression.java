package Statement.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Statement.ParseResult;
import Statement.UnexpectedTokenError;
import Token.Token;
import Token.TokenType;

public class TupleExpression extends Expression {
    private final Map<IdentifierExpression, Expression> expressionsMap;

    public TupleExpression(Map<IdentifierExpression, Expression> expressionsMap) {
        this.expressionsMap = expressionsMap;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.SEPARATOR) && token.isToken("{");
    }

    static public ParseResult<TupleExpression> parse(List<Token> tokens, int startTokenIndex) {
        int tokenIndex = startTokenIndex + 1;
        Token token = tokens.get(tokenIndex);
        Map<IdentifierExpression, Expression> expMap = new HashMap<>();
        boolean previousTokenIsSeparator = true;
        while(!token.isToken("}")) {
            if (!previousTokenIsSeparator && token.isToken(",")) {
                previousTokenIsSeparator = true;
                tokenIndex++;
            } else if (previousTokenIsSeparator) {
                previousTokenIsSeparator = false;
                ParseResult<IdentifierExpression> identifierParseResult = IdentifierExpression.parse(tokens, tokenIndex);
                tokenIndex = identifierParseResult.tokensParsed() + tokenIndex;
                token = tokens.get(tokenIndex);
                if (!token.isToken(":=")) throw new UnexpectedTokenError(token);
                tokenIndex++;
                ParseResult<Expression> expressionParseResult = Expression.parse(tokens, tokenIndex);
                tokenIndex = expressionParseResult.tokensParsed() + tokenIndex;
                expMap.put((IdentifierExpression) identifierParseResult.statement(), expressionParseResult.statement());
            } else {
                throw new UnexpectedTokenError(token);
            }
            
            token = tokens.get(tokenIndex);
        }
        tokenIndex++;
        return new ParseResult<TupleExpression>(new TupleExpression(expMap), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "TupleExpression{expressions=" + expressionsMap + "}";
    }
}
