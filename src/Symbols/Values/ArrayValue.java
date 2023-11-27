package Symbols.Values;

import Symbols.SymbolValue;
import Symbols.SymbolsError;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue extends Value {
    private final List<SymbolValue> elements;

    public ArrayValue(List<SymbolValue> elements) {
        this.elements = elements;
    }

    public List<SymbolValue> getElements() {
        return elements;
    }

    @Override
    public Value add(Value other) throws SymbolsError {
        if (other instanceof ArrayValue arrayValue) {
            List<SymbolValue> newElements = new ArrayList<>(elements);
            newElements.addAll(arrayValue.elements);
            return new ArrayValue(newElements);
        } else {
            return super.add(other);
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
