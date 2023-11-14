package Grammar.Statements;

import Symbols.ScopeType;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.IdentifierToken;
import Tokens.KeywordToken;
import Tokens.SeparatorToken;
import Tokens.Token;
import Grammar.Body;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class ForLoopStatement extends Statement {
    String identifier;
    Expression fromExpression;
    Expression toExpression;
    Body body;

    public ForLoopStatement(int startToken, int tokensCount, String identifier, Expression fromExpression, Expression toExpression, Body body) {
        super(startToken, tokensCount);
        this.identifier = identifier;
        this.fromExpression = fromExpression;
        this.toExpression = toExpression;
        this.body = body;
    }

    public static ForLoopStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        String identifier = null;
        Expression fromExpression;
        Expression toExpression = null;
        Body body;

        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("for"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        token = tokens.get(currentToken);
        if (token instanceof IdentifierToken) {
            identifier = ((IdentifierToken) token).getIdentifier();
            currentToken++;
            if (currentToken >= tokens.size()) return null;

            token = tokens.get(currentToken);
            if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("in"))) {
                return null;
            }
            currentToken++;
            if (currentToken >= tokens.size()) return null;
        }

        fromExpression = Expression.findNext(tokens, currentToken);
        if (fromExpression != null) {
            currentToken += fromExpression.getTokensCount();
            if (currentToken >= tokens.size()) return null;

            token = tokens.get(currentToken);
            if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(".."))) {
                return null;
            }
            currentToken++;
            if (currentToken >= tokens.size()) return null;

            toExpression = Expression.findNext(tokens, currentToken);
            if (toExpression == null) throw new SyntaxError(tokens.get(currentToken), "Expression is expected");
            currentToken += toExpression.getTokensCount();
            if (currentToken >= tokens.size()) return null;
        }

        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("loop"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        body = Body.findNext(tokens, currentToken);
        if (body == null) return null;
        currentToken += body.getTokensCount();
        if (currentToken >= tokens.size()) return null;

        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("end"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        return new ForLoopStatement(startToken, currentToken - startToken, identifier, fromExpression, toExpression, body);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        if (fromExpression != null) {
            fromExpression.analyse(symbolTable);
        }
        if (toExpression != null) {
            toExpression.analyse(symbolTable);
        }
        symbolTable.enterScope(ScopeType.FOR);
        if (identifier != null) {
            symbolTable.getCurrentScope().define(identifier);
        }
        body.analyse(symbolTable);
        symbolTable.exitScope();
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", identifier);
        fields.put("fromExpression", fromExpression);
        fields.put("toExpression", toExpression);
        fields.put("body", body);
        return fields;
    }
}
