package Grammar.Statements;

import Grammar.Expressions.Expression;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.KeywordToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class ReturnStatement extends Statement {
    Expression expression;

    public ReturnStatement(int startToken, int tokensCount, Expression expression) {
        super(startToken, tokensCount);
        this.expression = expression;
    }

    public static ReturnStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("return"))) {
            return null;
        }
        Expression expression = null;
        if (startToken + 1 < tokens.size()) {
            expression = Expression.findNext(tokens, startToken + 1);
        }
        int tokensCount = 1;
        if (expression != null) {
            tokensCount += expression.getTokensCount();
        }
        return new ReturnStatement(startToken, tokensCount, expression);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.getCurrentScope().expectFunctionScope();
        if (expression != null) {
            expression.analyse(symbolTable);
        }
    }

    @Override
    public Value execute(SymbolTable symbolTable) throws SymbolsError {
        if (expression != null) {
            return expression.evaluate(symbolTable);
        }
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("expression", expression);
        return fields;
    }
}
