package Symbols.Values;

import Symbols.SymbolsError;

public class StringValue extends Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getPrintableValue() {
        return value;
    }

    @Override
    public Value add(Value other) throws SymbolsError {
        if (other instanceof StringValue stringValue)
            return new StringValue(this.value + stringValue.getValue());
        else
            return super.add(other);
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
