package Symbols;

import Grammar.Grammar;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Scope currentScope;
    private final Map<Grammar, Scope> grammars;

    public SymbolTable() {
        currentScope = new Scope(ScopeType.GLOBAL, null);
        grammars = new HashMap<>();
    }

    public void enterScope(ScopeType type) {
        currentScope = new Scope(type, currentScope);
    }

    public void exitScope() throws SymbolsError {
        if (currentScope.getParent() == null) {
            throw new SymbolsError("Cannot exit scope");
        }
        currentScope = currentScope.getParent();
    }

    public Scope getCurrentScope() {
        return currentScope;
    }

    public void assignGrammar(Grammar grammar) {
        grammars.put(grammar, currentScope);
    }

    public Scope getScope(Grammar grammar) {
        return grammars.get(grammar);
    }
}
