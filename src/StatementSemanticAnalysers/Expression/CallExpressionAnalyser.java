package StatementSemanticAnalysers.Expression;

import Statement.Expression.CallExpression;
import Statement.Expression.IdentifierExpression;
import Statement.Expression.Expression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;
import Symbols.FunctionSymbol;

public class CallExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, CallExpression statement) throws SemanticAnalyserError {
        IdentifierExpression callee = statement.getCallee();
        IdentifierExpressionAnalyser.analyse(symbolTable, callee);

        String calleeName = callee.name();

        FunctionSymbol functionSymbol = symbolTable.lookupFunctionSymbol(calleeName);
        if (functionSymbol == null) {
            throw new SemanticAnalyserError(calleeName + " is not a function.");
        }

        System.out.println(functionSymbol);

        Integer expectedNumberOfArguments = functionSymbol.getParams().size();
        Integer factNumberOfArguments = statement.getParams().size();

        if (expectedNumberOfArguments != factNumberOfArguments) {
            throw new SemanticAnalyserError(
                "Function " 
                    + calleeName
                    + " expects " 
                    + expectedNumberOfArguments 
                    + " argument(s) instead of " 
                    + factNumberOfArguments
            );
        };

        for (Expression expression : statement.getParams()) {
            ExpressionAnalyser.analyse(symbolTable, expression);
        }
    }
}
