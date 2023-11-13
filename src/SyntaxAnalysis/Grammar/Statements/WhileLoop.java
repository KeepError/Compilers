package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Body;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class WhileLoop extends Statement {
    Expression whileExpression;
    Body body;

    public WhileLoop(int startToken, int tokensCount, Expression whileExpression, Body body) {
        super(startToken, tokensCount);
        this.whileExpression = whileExpression;
        this.body = body;
    }

    public static WhileLoop findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;

        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("while"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        Expression whileExpression = Expression.findNext(tokens, currentToken);
        if (whileExpression == null) return null;
        currentToken += whileExpression.getTokensCount();
        if (currentToken >= tokens.size()) return null;

        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("loop"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        Body body = Body.findNext(tokens, currentToken);
        if (body == null) return null;
        currentToken += body.getTokensCount();
        if (currentToken >= tokens.size()) return null;


        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("end"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        return new WhileLoop(startToken, currentToken - startToken, whileExpression, body);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("whileExpression", whileExpression);
        fields.put("body", body);
        return fields;
    }
}
