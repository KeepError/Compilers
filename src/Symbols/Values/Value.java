package Symbols.Values;

import Symbols.SymbolsError;

public abstract class Value {
    public Value add(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for addition: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value subtract(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for subtraction: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value multiply(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for multiplication: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value divide(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for division: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value unaryAdd() throws SymbolsError {
        throw new SymbolsError("Invalid type for unary addition: " + this.getClass().getSimpleName());
    }

    public Value unarySubtract() throws SymbolsError {
        throw new SymbolsError("Invalid type for unary subtraction: " + this.getClass().getSimpleName());
    }

    public Value logicalNot() throws SymbolsError {
        throw new SymbolsError("Invalid type for logical not: " + this.getClass().getSimpleName());
    }

    public Value logicalAnd(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for logical and: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value logicalOr(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for logical or: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value logicalXor(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for logical xor: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value less(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for less comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value lessEqual(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for less or equal comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value greater(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for greater comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value greaterEqual(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for greater or equal comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value equal(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for equal comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }

    public Value notEqual(Value other) throws SymbolsError {
        throw new SymbolsError("Invalid types for not equal comparison: " + this.getClass().getSimpleName() + " and " + other.getClass().getSimpleName());
    }
}
