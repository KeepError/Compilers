package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.Expression;
import Token.Token;
import Token.TokenType;

public class PrintStatement extends Expression {
    private final List<Expression> params;

    public PrintStatement(List<Expression> params) {
        this.params = params;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("print");
    }

    static public ParseResult<PrintStatement> parse(List<Token> tokens, int startTokenIndex) {
        int tokenIndex = startTokenIndex + 1;

        Pair<List<Expression>, Integer> params = PrintStatement.parseParams(tokens, tokenIndex);
        tokenIndex += params.secondValue();

        return new ParseResult<PrintStatement>(new PrintStatement(params.firstValue()), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "PrintExpression{params=" + params + "}";
    }

    private static Pair<List<Expression>, Integer> parseParams(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start;
        Token token = tokens.get(tokenIndex);

        boolean previousTokenIsSeparator = true;

        List<Expression> params = new ArrayList<>();
        while (!token.isToken(";")) {
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

    public List<Expression> getParams() {
        return this.params;
    }
}
