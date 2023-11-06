package StatementSemanticAnalysers.Expression;

import Statement.Expression.BinaryExpression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class BinaryExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, BinaryExpression statement) throws SemanticAnalyserError {
        System.out.println("Analysing binary expression...");
    }
}
