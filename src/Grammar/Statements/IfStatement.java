package Grammar.Statements;

import Grammar.Body;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;
import Symbols.ScopeType;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.BooleanValue;
import Symbols.Values.Value;
import Tokens.KeywordToken;
import Tokens.Token;

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
        if (conditionExpression == null) throw new SyntaxError(tokens.get(currentToken), "Expression is expected");
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
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        conditionExpression.analyse(symbolTable);
        symbolTable.enterScope(ScopeType.IF);
        thenBody.analyse(symbolTable);
        symbolTable.exitScope();
        if (elseBody != null) {
            symbolTable.enterScope(ScopeType.IF);
            elseBody.analyse(symbolTable);
            symbolTable.exitScope();
        }
    }

    @Override
    public Value execute(SymbolTable symbolTable) throws SymbolsError {
        Value conditionValue = conditionExpression.evaluate(symbolTable);
        boolean isTrue;
        if (conditionValue instanceof BooleanValue booleanValue) {
            isTrue = booleanValue.getValue();
        } else {
            throw new SymbolsError("Boolean condition is expected.");
        }
        symbolTable.enterScope(ScopeType.IF);
        Value returnValue = null;
        if (isTrue) {
            returnValue = thenBody.execute(symbolTable);
        } else if (elseBody != null) {
            returnValue = elseBody.execute(symbolTable);
        }
        symbolTable.exitScope();
        return returnValue;
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
