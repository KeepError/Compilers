package Statement;

import java.util.List;

import Token.Token;

public class Statement {
    public static ParseResult parse(List<Token> tokens, int start) {
        int tokenIndex = start;
        if (AssignmentStatement.isStart(tokens, tokenIndex)) {
            return AssignmentStatement.parse(tokens, tokenIndex);
        }
        if (FunctionDeclaration.isStart(tokens, tokenIndex)) {
            return FunctionDeclaration.parse(tokens, tokenIndex);
        }
        if (WhileStatement.isStart(tokens, tokenIndex)) {
            return WhileStatement.parse(tokens, tokenIndex);
        }
        if (ForStatement.isStart(tokens, tokenIndex)) {
            return ForStatement.parse(tokens, tokenIndex);
        }
        if (IfStatement.isStart(tokens, tokenIndex)) {
            return IfStatement.parse(tokens, tokenIndex);
        }
        throw new UnexpectedTokenError(tokens.get(tokenIndex));
    }
}
