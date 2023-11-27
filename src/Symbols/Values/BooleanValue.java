package Symbols.Values;

import Symbols.SymbolsError;

public class BooleanValue extends Value {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Value logicalNot() {
        return new BooleanValue(!value);
    }

    @Override
    public Value logicalAnd(Value other) throws SymbolsError {
        if (other instanceof BooleanValue booleanValue)
            return new BooleanValue(this.value && booleanValue.getValue());
        else
            return super.logicalAnd(other);
    }

    @Override
    public Value logicalOr(Value other) throws SymbolsError {
        if (other instanceof BooleanValue booleanValue)
            return new BooleanValue(this.value || booleanValue.getValue());
        else
            return super.logicalOr(other);
    }

    @Override
    public Value logicalXor(Value other) throws SymbolsError {
        if (other instanceof BooleanValue booleanValue)
            return new BooleanValue(this.value ^ booleanValue.getValue());
        else
            return super.logicalXor(other);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
