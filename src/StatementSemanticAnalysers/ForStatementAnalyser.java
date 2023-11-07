package StatementSemanticAnalysers;

import Statement.ForStatement;
import StatementSemanticAnalysers.Expression.IdentifierExpressionAnalyser;
import StatementSemanticAnalysers.Expression.ExpressionAnalyser;
import Symbols.LiteralSymbol;
import Symbols.SymbolTable;
import Symbols.ScopeType;

public class ForStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, ForStatement forStatement) throws SemanticAnalyserError {
        symbolTable.enterScope(ScopeType.FOR);
        System.out.println("Analysing for statement...");
        IdentifierExpressionAnalyser.declare(symbolTable, forStatement.getIdentifier(), new LiteralSymbol());
        ExpressionAnalyser.analyse(symbolTable, forStatement.getOfExpression());
        BlockStatementAnalyser.analyse(symbolTable, forStatement.getBody());
        symbolTable.exitScope();
    }
}
