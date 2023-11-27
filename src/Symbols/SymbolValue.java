package Symbols;

import Symbols.Values.Value;

public class SymbolValue {
    private Value value;

    public SymbolValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
