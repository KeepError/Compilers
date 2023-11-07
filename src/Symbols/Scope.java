package Symbols;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private ScopeType type;
    private Map<String, Symbol> symbols;

    public Scope(ScopeType type) {
        this.type = type;
        this.symbols = new HashMap<>();
    }

    public ScopeType getType() {
        return type;
    }

    public void addSymbol(String name, Symbol symbol) {
        symbols.put(name, symbol);
    }

    private Symbol findSymbol(String name) {
        return symbols.get(name);
    }

    public LiteralSymbol findLiteralSymbol(String name) {
        Symbol symbol = findSymbol(name);
        if (symbol instanceof LiteralSymbol) {
            return (LiteralSymbol) symbol;
        }
        return null;
    }

    public FunctionSymbol findFunctionSymbol(String name) {
        Symbol symbol = findSymbol(name);
        if (symbol instanceof FunctionSymbol) {
            return (FunctionSymbol) symbol;
        }
        return null;
    }
}
