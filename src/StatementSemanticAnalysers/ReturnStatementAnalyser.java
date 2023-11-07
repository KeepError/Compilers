package StatementSemanticAnalysers;

import Statement.ReturnStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.SymbolTable;
import Symbols.ScopeType;

public class ReturnStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, ReturnStatement returnStatement) throws SemanticAnalyserError {
        System.out.println("Analysing return statement...");
        if (symbolTable.getCurrentScope().getType() != ScopeType.FUNCTION) {
            throw new SemanticAnalyserError("Return statement is unexpected");
        }

        ExpressionAnalyser.analyse(symbolTable, returnStatement.getExpression());
    }
}