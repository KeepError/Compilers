package StatementSemanticAnalysers.Expression;

import Statement.Expression.IdentifierExpression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.Symbol;
import Symbols.SymbolTable;

public class IdentifierExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, IdentifierExpression statement) throws SemanticAnalyserError {
        System.out.println("Analysing identifier expression...");
        if (symbolTable.lookupVariableSymbol(statement.name()) == null) {
            throw new SemanticAnalyserError("Identifier " + statement.name() + " not found");
        }
    }

    public static void declare(SymbolTable symbolTable, IdentifierExpression identifier, Symbol symbol) {
       symbolTable.addSymbol(identifier.name(), symbol);
    }
}
