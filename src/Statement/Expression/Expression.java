package Statement.Expression;

import Statement.ParseResult;
import Statement.Statement;
import Statement.TokenExpectedError;
import Token.Token;

import java.util.List;

public class Expression extends Statement {
    
    public static ParseResult parseWithConfig(List<Token> tokens, int startTokenIndex, boolean isBinaryEnabled) {
        if (ArrayExpression.isStart(tokens, startTokenIndex)) return ArrayExpression.parse(tokens, startTokenIndex);
        if (TupleExpression.isStart(tokens, startTokenIndex)) return TupleExpression.parse(tokens, startTokenIndex);
        if (BinaryExpression.isStart(tokens, startTokenIndex) && isBinaryEnabled) return BinaryExpression.parse(tokens, startTokenIndex);
        if (LiteralExpression.isStart(tokens, startTokenIndex)) return LiteralExpression.parse(tokens, startTokenIndex);
        if (CallExpression.isStart(tokens, startTokenIndex)) return CallExpression.parse(tokens, startTokenIndex);
        if (IdentifierExpression.isStart(tokens, startTokenIndex)) return IdentifierExpression.parse(tokens, startTokenIndex);
        throw new TokenExpectedError(tokens.get(startTokenIndex));
    }
    
    public static ParseResult parse(List<Token> tokens, int startTokenIndex) {
        return parseWithConfig(tokens, startTokenIndex, true);
    }
}
