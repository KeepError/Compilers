package StatementSemanticAnalysers;

import Statement.FunctionDeclaration;
import Statement.Expression.IdentifierExpression;
import StatementSemanticAnalysers.Expression.IdentifierExpressionAnalyser;
import Symbols.FunctionSymbol;
import Symbols.LiteralSymbol;
import Symbols.SymbolTable;

public class FunctionStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, FunctionDeclaration functionDeclaration) throws SemanticAnalyserError {
        System.out.println("Analysing function statement...");
        FunctionSymbol symbol = new FunctionSymbol();
        IdentifierExpressionAnalyser.declare(symbolTable, functionDeclaration.getIdentifier(), symbol);
        symbolTable.enterScope();
        BlockStatementAnalyser.analyse(symbolTable, functionDeclaration.getBody());
        for (IdentifierExpression identifierExpression : functionDeclaration.getParams()) {
            IdentifierExpressionAnalyser.declare(symbolTable, identifierExpression, new LiteralSymbol());
        }
        symbolTable.exitScope();
    }
}