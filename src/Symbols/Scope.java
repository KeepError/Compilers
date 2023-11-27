package Symbols;

import Symbols.Values.EmptyValue;
import Symbols.Values.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Scope {
    private final Scope parent;
    private final Map<String, SymbolValue> symbols;
    private final Set<String> references;
    private final ScopeType type;

    public Scope(ScopeType type, Scope parent) {
        this.parent = parent;
        this.symbols = new HashMap<>();
        this.references = new HashSet<>();
        this.type = type;
    }

    public Scope getParent() {
        return parent;
    }

    public void define(String name) throws SymbolsError {
        if (symbols.containsKey(name)) {
            throw new SymbolsError("Symbol " + name + " already exists in this scope");
        }
        Value value = new EmptyValue();
        symbols.put(name, new SymbolValue(value));
    }

    public void reference(String name) throws SymbolsError {
        if (symbols.containsKey(name)) {
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

    public SymbolValue getSymbolValue(String name) throws SymbolsError {
        if (symbols.containsKey(name)) {
            return symbols.get(name);
        }
        if (parent != null) {
            return parent.getSymbolValue(name);
        }
        throw new SymbolsError("Symbol " + name + " is not defined");
    }
}