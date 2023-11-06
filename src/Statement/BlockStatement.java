package Statement;

import java.util.ArrayList;
import java.util.List;

import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import Token.Token;
import Token.TokenType;

public class BlockStatement extends Statement {
    private final List<Statement> statements;

    public BlockStatement(List<Statement> statements) {
        // statements = new ArrayList<>()
        this.statements = statements;
    }

    public List<Statement> statements() {
        return statements;
    }

    // public void addStatement(Statement statement) {
    //     statements.add(statement);
    // }

    static public ParseResult parse(List<Token> tokens, int startTokenIndex) {
        return BlockStatement.parse(tokens, startTokenIndex, true);
    }

    static public ParseResult parse(List<Token> tokens, int startTokenIndex, boolean includeEnd) {
        Token token = tokens.get(startTokenIndex);
        int tokenIndex = startTokenIndex;
        List<Statement> statements = new ArrayList<>();
        while (tokenIndex < tokens.size() && (!includeEnd || !(tokens.get(tokenIndex).isType(TokenType.KEYWORD) && tokens.get(tokenIndex).isToken("end")))) {
            ParseResult parseResult = Statement.parse(tokens, tokenIndex);
            tokenIndex += parseResult.tokensParsed();
            statements.add(parseResult.statement());
        }
        if (tokenIndex < tokens.size()) {
            token = tokens.get(tokenIndex);
        } else {
            token = null;
        }
        if (includeEnd && (token == null || !(token.isType(TokenType.KEYWORD) && token.isToken("end")) ))
            throw new UnexpectedTokenError(token);
        if (includeEnd) tokenIndex++;
        return new ParseResult(new BlockStatement(statements), tokenIndex - startTokenIndex);
    }

    @Override
    public String toString() {
        return "BlockStatement{statements=" + statements + "}";
    }
}
