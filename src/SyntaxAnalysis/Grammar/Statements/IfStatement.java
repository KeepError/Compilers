package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Body;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class IfStatement extends Statement {
    Expression conditionExpression;
    Body thenBody;
    Body elseBody;

    public IfStatement(int startToken, int tokensCount, Expression conditionExpression, Body thenBody, Body elseBody) {
        super(startToken, tokensCount);
        this.conditionExpression = conditionExpression;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public static IfStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("if"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        Expression conditionExpression = Expression.findNext(tokens, currentToken);
        if (conditionExpression == null) return null;
        currentToken += conditionExpression.getTokensCount();
        if (currentToken >= tokens.size()) return null;
        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("then"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        Body thenBody = Body.findNext(tokens, currentToken);
        if (thenBody == null) return null;
        currentToken += thenBody.getTokensCount();
        if (currentToken >= tokens.size()) return null;
        token = tokens.get(currentToken);
        Body elseBody = null;
        if (token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("else")) {
            currentToken++;
            if (currentToken >= tokens.size()) return null;
            elseBody = Body.findNext(tokens, currentToken);
            if (elseBody == null) return null;
            currentToken += elseBody.getTokensCount();
            if (currentToken >= tokens.size()) return null;
        }
        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("end"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        return new IfStatement(startToken, currentToken - startToken, conditionExpression, thenBody, elseBody);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("conditionExpression", conditionExpression);
        fields.put("thenBody", thenBody);
        fields.put("elseBody", elseBody);
        return fields;
    }
}
