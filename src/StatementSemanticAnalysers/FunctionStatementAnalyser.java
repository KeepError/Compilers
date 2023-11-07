package StatementSemanticAnalysers;

import Statement.FunctionDeclaration;
import Statement.Expression.IdentifierExpression;
import StatementSemanticAnalysers.Expression.IdentifierExpressionAnalyser;
import Symbols.FunctionSymbol;
import Symbols.LiteralSymbol;
import Symbols.SymbolTable;
import java.util.Map;
import java.util.HashMap;
import Symbols.ScopeType;

public class FunctionStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, FunctionDeclaration functionDeclaration) throws SemanticAnalyserError {
        System.out.println("Analysing function statement...");
        Map<String, LiteralSymbol> literalSymbolsMap = new HashMap<String, LiteralSymbol>();
        for (IdentifierExpression identifierExpression : functionDeclaration.getParams()) {
            literalSymbolsMap.put(identifierExpression.name(), new LiteralSymbol());
        }
        FunctionSymbol symbol = new FunctionSymbol(literalSymbolsMap);

        IdentifierExpressionAnalyser.declare(symbolTable, functionDeclaration.getIdentifier(), symbol);
        symbolTable.enterScope(ScopeType.FUNCTION);
        BlockStatementAnalyser.analyse(symbolTable, functionDeclaration.getBody());
        for (IdentifierExpression identifierExpression : functionDeclaration.getParams()) {
            IdentifierExpressionAnalyser.declare(symbolTable, identifierExpression, literalSymbolsMap.get(identifierExpression.name()));
        }
        symbolTable.exitScope();
    }
}