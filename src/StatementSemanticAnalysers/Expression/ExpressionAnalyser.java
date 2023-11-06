package StatementSemanticAnalysers.Expression;

import Statement.Expression.ArrayExpression;
import Statement.Expression.BinaryExpression;
import Statement.Expression.CallExpression;
import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import Statement.Expression.LiteralExpression;
import Statement.Expression.TupleExpression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class ExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, Expression statement) throws SemanticAnalyserError {
        if (statement instanceof IdentifierExpression) {
            IdentifierExpressionAnalyser.analyse(symbolTable, (IdentifierExpression) statement);
        } else if (statement instanceof ArrayExpression) {
            ArrayExpressionAnalyser.analyse(symbolTable, (ArrayExpression) statement);
        } else if (statement instanceof BinaryExpression) {
            BinaryExpressionAnalyser.analyse(symbolTable, (BinaryExpression) statement);
        } else if (statement instanceof CallExpression) {
            CallExpressionAnalyser.analyse(symbolTable, (CallExpression) statement);
        } else if (statement instanceof LiteralExpression) {
            LiteralExpressionAnalyser.analyse(symbolTable, (LiteralExpression) statement);
        } else if (statement instanceof TupleExpression) {
            TupleExpressionAnalyser.analyse(symbolTable, (TupleExpression) statement);
        } else {
            System.out.println("Expression analyser not found");
        }
    }
}
