package SyntaxAnalysis.Grammar.Expressions.Primary;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class PrimaryBracketedExpression extends PrimaryExpression {
    private final Expression expression;

    public PrimaryBracketedExpression(int startToken, int tokensCount, Expression expression) {
        super(startToken, tokensCount);
        this.expression = expression;
    }

    public static PrimaryBracketedExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("("))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        Expression expression = Expression.findNext(tokens, currentToken);
        if (expression == null) return null;
        currentToken += expression.getTokensCount();
        token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(")"))) {
            return null;
        }
        currentToken++;
        return new PrimaryBracketedExpression(startToken, currentToken - startToken, expression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("expression", expression);
        return fields;
    }
}
