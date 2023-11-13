package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

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
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("expressions", expressions);
        return fields;
    }
}
