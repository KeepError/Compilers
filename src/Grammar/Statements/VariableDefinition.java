package Grammar.Statements;

import Grammar.Expressions.Expression;
import Grammar.Grammar;
import Grammar.SyntaxError;
import Symbols.Scope;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.IdentifierToken;
import Tokens.OperatorToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class VariableDefinition extends Grammar {
    private final String identifier;
    private final Expression value;

    public VariableDefinition(int startToken, int tokensCount, String identifier, Expression value) {
        super(startToken, tokensCount);
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static VariableDefinition findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof IdentifierToken)) {
            return null;
        }
        String identifier = ((IdentifierToken) token).getIdentifier();
        currentToken++;
        if (currentToken >= tokens.size()) return null;

        Expression value = null;

        token = tokens.get(currentToken);
        if (token instanceof OperatorToken && ((OperatorToken) token).getOperator().equals(":=")) {
            currentToken++;
            if (currentToken >= tokens.size()) return null;

            value = Expression.findNext(tokens, currentToken);
            if (value == null) return null;
            currentToken += value.getTokensCount();
            if (currentToken >= tokens.size()) return null;
        }

        return new VariableDefinition(startToken, currentToken - startToken, identifier, value);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.assignGrammar(this);
        symbolTable.getCurrentScope().define(identifier);
        if (value != null) {
            value.analyse(symbolTable);
        }
    }

    public void define(SymbolTable symbolTable) throws SymbolsError {
        Scope scope = symbolTable.getCurrentScope();
        scope.define(identifier);
        if (value == null) return;
        scope.getSymbolValue(identifier).setValue(value.evaluate(symbolTable));
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", identifier);
        fields.put("value", value);
        return fields;
    }
}
