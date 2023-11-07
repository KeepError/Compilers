package StatementSemanticAnalysers;

import Statement.WhileStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.SymbolTable;
import Symbols.ScopeType;

public class WhileStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, WhileStatement whileStatement) throws SemanticAnalyserError {
        symbolTable.enterScope(ScopeType.WHILE);
        System.out.println("Analysing while statement...");
        BlockStatementAnalyser.analyse(symbolTable, whileStatement.getBody());
        ExpressionAnalyser.analyse(symbolTable, whileStatement.getTest());
        symbolTable.exitScope();
    }
}