package Symbols;

import java.util.HashSet;
import java.util.Set;

public class Scope {
    private final Scope parent;
    private final Set<String> symbols;
    private final Set<String> references;
    private final ScopeType type;

    public Scope(ScopeType type, Scope parent) {
        this.parent = parent;
        this.symbols = new HashSet<>();
        this.references = new HashSet<>();
        this.type = type;
    }

    public Scope getParent() {
        return parent;
    }

    public void define(String name) throws SymbolsError {
        if (symbols.contains(name)) {
            throw new SymbolsError("Symbol " + name + " already exists in this scope");
        }
        symbols.add(name);
    }

    public void reference(String name) throws SymbolsError {
        if (symbols.contains(name)) {
            references.add(name);
            return;
        }
        if (parent != null) {
            parent.reference(name);
            return;
        }
        throw new SymbolsError("Symbol " + name + " is not defined");
    }

    public boolean isReferenced(String name) {
        if (references.contains(name)) {
            return true;
        }
        if (parent != null) {
            return parent.isReferenced(name);
        }
        return false;
    }

    public void expectFunctionScope() throws SymbolsError {
        if (type == ScopeType.FUNCTION) {
            return;
        }
        if (parent != null) {
            parent.expectFunctionScope();
            return;
        }
        throw new SymbolsError("Function scope is expected");
    }
}