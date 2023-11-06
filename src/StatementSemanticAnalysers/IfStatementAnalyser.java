package StatementSemanticAnalysers;

import Statement.IfStatement;
import Symbols.SymbolTable;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;

public class IfStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, IfStatement ifStatement) throws SemanticAnalyserError {
        symbolTable.enterScope();
        System.out.println("Analysing if statement...");
        BlockStatementAnalyser.analyse(symbolTable, ifStatement.getBody());
        ExpressionAnalyser.analyse(symbolTable, ifStatement.getTest());
        symbolTable.exitScope();
    }
}
