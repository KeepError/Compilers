package StatementSemanticAnalysers;

import Statement.ReturnStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.SymbolTable;

public class ReturnStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, ReturnStatement returnStatement) throws SemanticAnalyserError {
        System.out.println("Analysing return statement...");
        ExpressionAnalyser.analyse(symbolTable, returnStatement.getExpression());
    }
}