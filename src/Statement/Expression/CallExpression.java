package Statement.Expression;

import Statement.Pair;
import Statement.ParseResult;
import Statement.TokenError;
import Statement.UnexpectedTokenError;
import Token.Token;
import Token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class CallExpression extends Expression {
    private final IdentifierExpression callee;
    private final List<Expression> params;

    public CallExpression(IdentifierExpression callee, List<Expression> params) {
        this.callee = callee;
        this.params = params;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        try {
            Token nextToken = tokens.get(startTokenIndex+1);
            return IdentifierExpression.isStart(tokens, startTokenIndex) && nextToken.isType(TokenType.SEPARATOR) && nextToken.isToken("(");
        } catch (Exception e) {
            return false;
        }
    }

    static public ParseResult<CallExpression> parse(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        int tokenIndex = startTokenIndex;
        ParseResult<IdentifierExpression> identifier = IdentifierExpression.parse(tokens, startTokenIndex);
        tokenIndex += identifier.tokensParsed();

        token = tokens.get(startTokenIndex);
        Pair<List<Expression>, Integer> params = CallExpression.parseParams(tokens, tokenIndex);
        tokenIndex += params.secondValue();

        return new ParseResult<CallExpression>(new CallExpression((IdentifierExpression) identifier.statement(), params.firstValue()), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "CallExpression{callee=" + callee + ",params=" + params + "}";
    }

    private static Pair<List<Expression>, Integer> parseParams(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);

        boolean previousTokenIsSeparator = true;

        List<Expression> params = new ArrayList<>();
        while(!token.isToken(")")) {
            if (!previousTokenIsSeparator && token.isToken(",")) {
                previousTokenIsSeparator = true;
                tokenIndex++;
            } else if (previousTokenIsSeparator) {
                previousTokenIsSeparator = false;
                ParseResult<Expression> paramParseResult = Expression.parse(tokens, tokenIndex);
                tokenIndex = paramParseResult.tokensParsed() + tokenIndex;
                params.add((Expression) paramParseResult.statement());
            } else {
                throw new UnexpectedTokenError(token);
            }
            
            token = tokens.get(tokenIndex);
        }
        tokenIndex++;
        
        return new Pair<List<Expression>, Integer>(params, tokenIndex - start);
    }
}