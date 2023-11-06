package StatementSemanticAnalysers;

import Statement.Statement;
import Statement.VariableDeclarationStatement;
import Statement.IfStatement;
import Statement.WhileStatement;
import Statement.FunctionDeclaration;
import Statement.ReturnStatement;
import Statement.PrintStatement;
import Statement.AssignmentStatement;
import Symbols.SymbolTable;

public class StatementAnalyser {
    public static void analyse(SymbolTable symbolTable, Statement statement) throws SemanticAnalyserError {
        System.out.println("Analysing statement...");
        if (statement instanceof VariableDeclarationStatement) {
            VariableDeclarationStatementAnalyser.analyse(symbolTable, (VariableDeclarationStatement) statement);
        } else if (statement instanceof IfStatement) {
            IfStatementAnalyser.analyse(symbolTable, (IfStatement) statement);
        } else if (statement instanceof WhileStatement) {
            WhileStatementAnalyser.analyse(symbolTable, (WhileStatement) statement);
        } else if (statement instanceof FunctionDeclaration) {
            FunctionStatementAnalyser.analyse(symbolTable, (FunctionDeclaration) statement);
        } else if (statement instanceof ReturnStatement) {
            ReturnStatementAnalyser.analyse(symbolTable, (ReturnStatement) statement);
        } else if (statement instanceof PrintStatement) {
            PrintStatementAnalyser.analyse(symbolTable, (PrintStatement) statement);
        } else if (statement instanceof AssignmentStatement) {
            AssignmentStatementAnalyser.analyse(symbolTable, (AssignmentStatement) statement);
        } else {
            System.out.println("Statement analyser not found");
        }
    }
}
