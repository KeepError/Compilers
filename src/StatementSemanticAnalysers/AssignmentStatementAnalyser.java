package StatementSemanticAnalysers;

import Statement.AssignmentStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import StatementSemanticAnalysers.Expression.IdentifierExpressionAnalyser;
import Symbols.SymbolTable;

public class AssignmentStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, AssignmentStatement assignmentStatement) throws SemanticAnalyserError {
        System.out.println("Analysing assignment statement...");
        ExpressionAnalyser.analyse(symbolTable, assignmentStatement.getExpression());
        IdentifierExpressionAnalyser.analyse(symbolTable, assignmentStatement.getIdentifier());
    }
}