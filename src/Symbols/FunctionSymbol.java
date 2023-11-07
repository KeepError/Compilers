package Symbols;

import java.util.Map;

public class FunctionSymbol extends Symbol {
    private Map<String, LiteralSymbol> params;

    public FunctionSymbol(Map<String, LiteralSymbol> params) {
        this.params = params;
    }

    public Map<String, LiteralSymbol> getParams() {
        return this.params;
    }
}
