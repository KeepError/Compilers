package StatementSemanticAnalysers.Expression;

import Statement.Expression.CallExpression;
import Statement.Expression.IdentifierExpression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class CallExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, CallExpression statement) throws SemanticAnalyserError {
        IdentifierExpression callee = statement.getCallee();
        IdentifierExpressionAnalyser.analyse(symbolTable, callee);

        
    }
}
