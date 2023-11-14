package Grammar.Statements;

import Symbols.ScopeType;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.Body;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class WhileLoopStatement extends Statement {
    Expression whileExpression;
    Body body;

    public WhileLoopStatement(int startToken, int tokensCount, Expression whileExpression, Body body) {
        super(startToken, tokensCount);
        this.whileExpression = whileExpression;
        this.body = body;
    }

    public static WhileLoopStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;

        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("while"))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        Expression whileExpression = Expression.findNext(tokens, currentToken);
        if (whileExpression == null) throw new SyntaxError(tokens.get(currentToken), "Expression is expected");
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

        return new WhileLoopStatement(startToken, currentToken - startToken, whileExpression, body);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.assignGrammar(this);
        whileExpression.analyse(symbolTable);
        symbolTable.enterScope(ScopeType.WHILE);
        body.analyse(symbolTable);
        symbolTable.exitScope();
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("whileExpression", whileExpression);
        fields.put("body", body);
        return fields;
    }
}
