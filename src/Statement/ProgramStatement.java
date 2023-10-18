package Statement;

import java.util.List;

import Token.Token;

public class ProgramStatement extends Statement {
    private final BlockStatement body;

    public ProgramStatement(BlockStatement body) {
        this.body = body;
    }

    // public void addBodyStatement(Statement statement) {
    //     body.addStatement(statement);
    // }

    public static ParseResult parse(List<Token> tokens, int start) throws TokenError {
        int tokenIndex = start;
        ParseResult parseResult = BlockStatement.parse(tokens, tokenIndex, false);
        ProgramStatement programStatement = new ProgramStatement((BlockStatement) parseResult.statement());
        // while (tokenIndex < tokens.size()) {
        //     ParseResult parseResult = Statement.parse(tokens, tokenIndex);
        //     tokenIndex += parseResult.tokensParsed();
        //     programStatement.addBodyStatement(parseResult.statement());
        // }
        return new ParseResult(programStatement, tokenIndex - start);
    }

    @Override
    public String toString() {
        return "ProgramStatement{body=" + body + "}";
    }
}
