package Symbols;

import java.util.HashSet;
import java.util.Set;

public class Scope {
    private final Set<String> symbols;

    public Scope() {
        this.symbols = new HashSet<>();
    }

    public void addSymbol(String name) throws SymbolsError {
        if (symbols.contains(name)) {
            throw new SymbolsError("Symbol " + name + " already exists in this scope");
        }
        symbols.add(name);
    }

    public boolean containsSymbol(String name) {
        return symbols.contains(name);
    }
}