package StatementSemanticAnalysers;

import Statement.VariableDeclarationStatement;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import StatementSemanticAnalysers.Expression.IdentifierExpressionAnalyser;
import Symbols.SymbolTable;
import Symbols.LiteralSymbol;

public class VariableDeclarationStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, VariableDeclarationStatement statement) throws SemanticAnalyserError {
        System.out.println("Analysing variable declaration statement...");
        ExpressionAnalyser.analyse(symbolTable, statement.expression());
        IdentifierExpressionAnalyser.declare(symbolTable, statement.identifier(), new LiteralSymbol());
    }
}
