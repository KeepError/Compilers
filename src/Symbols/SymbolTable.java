package Symbols;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {
    private Stack<Map<String, Symbol>> scopes;

    public SymbolTable() {
        scopes = new Stack<>();
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        scopes.pop();
    }

    public void addSymbol(String name, Symbol symbol) {
        scopes.peek().put(name, symbol);
    }

    private Symbol lookupSymbol(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Symbol symbol = scopes.get(i).get(name);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }

    public LiteralSymbol lookupVariableSymbol(String name) {
        Symbol symbol = lookupSymbol(name);
        if (symbol instanceof LiteralSymbol) {
            return (LiteralSymbol) symbol;
        }
        return null;
    }

    public FunctionSymbol lookupFunctionSymbol(String name) {
        Symbol symbol = lookupSymbol(name);
        if (symbol instanceof FunctionSymbol) {
            return (FunctionSymbol) symbol;
        }
        return null;
    }
}
