package Grammar.Statements;

import Grammar.Expressions.Expression;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.KeywordToken;
import Tokens.SeparatorToken;
import Tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintStatement extends Statement {
    List<Expression> expressions;

    public PrintStatement(int startToken, int tokensCount, List<Expression> expressions) {
        super(startToken, tokensCount);
        this.expressions = expressions;
    }

    public static PrintStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("print"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        List<Expression> expressions = new ArrayList<>();
        int separatorCount = 0;
        boolean noSeparator = false;
        do {
            if (separatorCount == expressions.size()) {
                Expression expression = Expression.findNext(tokens, currentToken);
                if (expression == null) throw new SyntaxError(tokens.get(currentToken), "Expression is expected");
                expressions.add(expression);
                currentToken += expression.getTokensCount();
            } else if (separatorCount == expressions.size() - 1) {
                token = tokens.get(currentToken);
                if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(",")) {
                    currentToken++;
                    separatorCount++;
                } else {
                    noSeparator = true;
                }
            } else {
                return null;
            }
            if (currentToken >= tokens.size()) return null;
        } while (!noSeparator);

        return new PrintStatement(startToken, currentToken - startToken, expressions);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        for (Expression expression : expressions) {
            expression.analyse(symbolTable);
        }
    }

    @Override
    public Value execute(SymbolTable symbolTable) throws SymbolsError {
        StringBuilder stringBuilder = new StringBuilder();
        for (Expression expression : expressions) {
            stringBuilder.append(expression.evaluate(symbolTable).getPrintableValue());
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder);
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("expressions", expressions);
        return fields;
    }
}
