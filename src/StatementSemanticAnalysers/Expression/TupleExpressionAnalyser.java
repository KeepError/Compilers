package StatementSemanticAnalysers.Expression;

import java.util.Map;

import Statement.Expression.TupleExpression;
import Statement.Expression.Expression;
import Statement.Expression.IdentifierExpression;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class TupleExpressionAnalyser {
    public static void analyse(SymbolTable symbolTable, TupleExpression statement) throws SemanticAnalyserError {
        for (Map.Entry<IdentifierExpression, Expression> entry : statement.getExpressionsMap().entrySet()) {
            ExpressionAnalyser.analyse(symbolTable, entry.getValue());
        }
    }
}
