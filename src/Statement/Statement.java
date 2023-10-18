package Statement;

import java.util.List;

import Statement.Expression.Expression;
import Token.Token;

public class Statement {
    public static ParseResult parse(List<Token> tokens, int start) {
        int tokenIndex = start;
        if (AssignmentStatement.isStart(tokens, start)) {
            return AssignmentStatement.parse(tokens, start);
        }
        if (VariableDeclarationStatement.isStart(tokens, tokenIndex)) {
            return VariableDeclarationStatement.parse(tokens, tokenIndex);
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
        if (PrintStatement.isStart(tokens, tokenIndex)) {
            return PrintStatement.parse(tokens, tokenIndex);
        }
        if (ReturnStatement.isStart(tokens, tokenIndex)) {
            return ReturnStatement.parse(tokens, tokenIndex);
        }
        return ExpressionStatement.parse(tokens, tokenIndex);
    }
}
