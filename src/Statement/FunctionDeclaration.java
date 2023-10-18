package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.IdentifierExpression;
import Token.Token;
import Token.TokenType;

public class FunctionDeclaration extends Statement {
    private final IdentifierExpression identifier;
    private final BlockStatement body;
    private final List<IdentifierExpression> params;

    public FunctionDeclaration(IdentifierExpression identifier, BlockStatement body, List<IdentifierExpression> params) {
        this.identifier = identifier;
        this.body = body;
        this.params = params;
    }

    static public boolean isStart(List<Token> tokens, int startTokenIndex) {
        Token token = tokens.get(startTokenIndex);
        return token.isType(TokenType.KEYWORD) && token.isToken("func");
    }

    public static ParseResult<FunctionDeclaration> parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);
        
        if (!IdentifierExpression.isStart(tokens, tokenIndex))
            throw new UnexpectedTokenError(token);
        ParseResult<IdentifierExpression> identifier = IdentifierExpression.parse(tokens, tokenIndex);
        tokenIndex += identifier.tokensParsed();
        token = tokens.get(tokenIndex);
        
        if (!token.isToken("("))
            throw new UnexpectedTokenError(token);
        Pair<List<IdentifierExpression>, Integer> params = FunctionDeclaration.parseParams(tokens, tokenIndex);
        tokenIndex += params.secondValue();
        token = tokens.get(tokenIndex);

        if (!token.isToken("is"))
            throw new UnexpectedTokenError(token);
        ParseResult<BlockStatement> body = BlockStatement.parse(tokens, ++tokenIndex);
        tokenIndex += body.tokensParsed();
        
        FunctionDeclaration functionDeclaration = new FunctionDeclaration((IdentifierExpression) identifier.statement(), (BlockStatement) body.statement(), (List<IdentifierExpression>) params.firstValue());
        return new ParseResult(functionDeclaration, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "FunctionDeclaration{identifier="+ identifier +",body=[" + body + "],params="+ params +"}";
    }

    private static Pair<List<IdentifierExpression>, Integer> parseParams(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start + 1;
        Token token = tokens.get(tokenIndex);

        boolean previousTokenIsSeparator = true;

        List<IdentifierExpression> params = new ArrayList<>();
        while(!token.isToken(")")) {
            if (!previousTokenIsSeparator && token.isToken(",")) {
                previousTokenIsSeparator = true;
                tokenIndex++;
            } else if (previousTokenIsSeparator && token.isType(TokenType.IDENTIFIER)) {
                previousTokenIsSeparator = false;
                ParseResult<IdentifierExpression> paramParseResult = IdentifierExpression.parse(tokens, tokenIndex);
                tokenIndex = paramParseResult.tokensParsed() + tokenIndex;
                params.add((IdentifierExpression) paramParseResult.statement());
            } else {
                throw new UnexpectedTokenError(token);
            }
            
            token = tokens.get(tokenIndex);
        }
        tokenIndex++;
        
        return new Pair<List<IdentifierExpression>, Integer>(params, tokenIndex - start);
    }
}
