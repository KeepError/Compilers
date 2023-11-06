package StatementSemanticAnalysers.Expression;

import Statement.Statement;
import Statement.Expression.ArrayExpression;
import Statement.Expression.Expression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class ArrayExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, ArrayExpression statement) throws SemanticAnalyserError {
        for (Statement s : statement.getExpressions()) {
            ExpressionAnalyser.analyse(symbolTable, (Expression) s);
        }
    }
}
