package Symbols;

import java.util.Stack;

public class SymbolTable {
    private final Stack<Scope> scopes;

    public SymbolTable() {
        scopes = new Stack<>();
    }

    public void enterScope() throws SymbolsError {
        scopes.push(new Scope());
    }

    public void exitScope() throws SymbolsError {
        if (scopes.empty()) {
            throw new SymbolsError("Cannot exit scope");
        }
        scopes.pop();
    }

    public void addSymbol(String name) throws SymbolsError {
        scopes.peek().addSymbol(name);
    }

    public void referenceSymbol(String name) throws SymbolsError {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsSymbol(name)) {
                return;
            }
        }
        throw new SymbolsError("Symbol " + name + " is not defined");
    }

    public void checkUnclosedScopes() throws SymbolsError {
        if (!scopes.empty()) {
            throw new SymbolsError("Not all scopes exited");
        }
    }
}
