package StatementSemanticAnalysers;

import Statement.WhileStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.SymbolTable;

public class WhileStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, WhileStatement whileStatement) throws SemanticAnalyserError {
        symbolTable.enterScope();
        System.out.println("Analysing while statement...");
        BlockStatementAnalyser.analyse(symbolTable, whileStatement.getBody());
        ExpressionAnalyser.analyse(symbolTable, whileStatement.getTest());
        symbolTable.exitScope();
    }
}