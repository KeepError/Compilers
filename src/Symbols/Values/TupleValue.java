package Symbols.Values;

import Symbols.SymbolValue;
import Symbols.SymbolsError;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class TupleValue extends Value {
    private final Map<String, SymbolValue> namedElements;
    private final Map<Integer, SymbolValue> unnamedElements;

    public TupleValue(Map<String, SymbolValue> namedElements, Map<Integer, SymbolValue> unnamedElements) {
        this.unnamedElements = unnamedElements;
        this.namedElements = namedElements;
    }

    public Map<String, SymbolValue> getNamedElements() {
        return namedElements;
    }

    public Map<Integer, SymbolValue> getUnnamedElements() {
        return unnamedElements;
    }

    @Override
    public Value add(Value other) throws SymbolsError {
        if (other instanceof TupleValue tupleValue) {
            Map<String, SymbolValue> newNamedElements = new HashMap<>(namedElements);
            Map<Integer, SymbolValue> newUnnamedElements = new HashMap<>(unnamedElements);

            for (Map.Entry<String, SymbolValue> entry : tupleValue.namedElements.entrySet()) {
                if (newNamedElements.containsKey(entry.getKey())) {
                    throw new SymbolsError("Duplicate key in tuple: " + entry.getKey());
                }
                newNamedElements.put(entry.getKey(), entry.getValue());
            }

            int offset = namedElements.size() + unnamedElements.size();

            for (Map.Entry<Integer, SymbolValue> entry : tupleValue.unnamedElements.entrySet()) {
                newUnnamedElements.put(entry.getKey() + offset, entry.getValue());
            }

            return new TupleValue(newNamedElements, newUnnamedElements);
        } else {
            return super.add(other);
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for (Map.Entry<String, SymbolValue> entry : namedElements.entrySet()) {
            joiner.add(entry.getKey() + ": " + entry.getValue());
        }
        for (Map.Entry<Integer, SymbolValue> entry : unnamedElements.entrySet()) {
            joiner.add(entry.getKey() + ": " + entry.getValue());
        }
        return joiner.toString();
    }
}
