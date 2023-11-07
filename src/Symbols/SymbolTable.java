package Symbols;

import java.util.Stack;

public class SymbolTable {
    private Stack<Scope> scopes;

    public SymbolTable() {
        scopes = new Stack<>();
    }

    public void enterScope(ScopeType scopeType) {
        scopes.push(new Scope(scopeType));
    }

    public void exitScope() {
        scopes.pop();
    }

    public Scope getCurrentScope() {
        return scopes.peek();
    }

    public void addSymbol(String name, Symbol symbol) {
        scopes.peek().addSymbol(name, symbol);
    }

    public LiteralSymbol lookupVariableSymbol(String name) {
        for (Scope scope : scopes) {
            LiteralSymbol symbol = scope.findLiteralSymbol(name);
            if (symbol != null) return symbol;
        }
        return null;
    }

    public FunctionSymbol lookupFunctionSymbol(String name) {
        for (Scope scope : scopes) {
            FunctionSymbol symbol = scope.findFunctionSymbol(name);
            if (symbol != null) return symbol;
        }
        return null;
    }
}
