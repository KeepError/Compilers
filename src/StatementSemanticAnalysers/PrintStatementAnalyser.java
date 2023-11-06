package StatementSemanticAnalysers;

import Statement.PrintStatement;
import Statement.Expression.Expression;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.SymbolTable;

public class PrintStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, PrintStatement printStatement) throws SemanticAnalyserError {
        System.out.println("Analysing print statement...");
        for (Expression expression : printStatement.getParams()) {
            ExpressionAnalyser.analyse(symbolTable, expression);
        }
    }
}