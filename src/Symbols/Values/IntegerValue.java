package Symbols.Values;

import Symbols.SymbolsError;

public class IntegerValue extends Value {
    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Value add(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new IntegerValue(this.value + integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new RealValue(this.value + realValue.getValue());
        else
            return super.add(other);
    }

    @Override
    public Value subtract(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new IntegerValue(this.value - integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new RealValue(this.value - realValue.getValue());
        else
            return super.subtract(other);
    }

    @Override
    public Value multiply(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new IntegerValue(this.value * integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new RealValue(this.value * realValue.getValue());
        else
            return super.multiply(other);
    }

    @Override
    public Value divide(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new IntegerValue(this.value / integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new RealValue(this.value / realValue.getValue());
        else
            return super.divide(other);
    }

    @Override
    public Value unaryAdd() {
        return new IntegerValue(this.value);
    }

    @Override
    public Value unarySubtract() {
        return new IntegerValue(-this.value);
    }

    @Override
    public Value less(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value < integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value < realValue.getValue());
        else
            return super.less(other);
    }

    @Override
    public Value lessEqual(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value <= integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value <= realValue.getValue());
        else
            return super.lessEqual(other);
    }

    @Override
    public Value greater(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value > integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value > realValue.getValue());
        else
            return super.greater(other);
    }

    @Override
    public Value greaterEqual(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value >= integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value >= realValue.getValue());
        else
            return super.greaterEqual(other);
    }

    @Override
    public Value equal(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value == integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value == realValue.getValue());
        else
            return super.equal(other);
    }

    @Override
    public Value notEqual(Value other) throws SymbolsError {
        if (other instanceof IntegerValue integerValue)
            return new BooleanValue(this.value != integerValue.getValue());
        else if (other instanceof RealValue realValue)
            return new BooleanValue(this.value != realValue.getValue());
        else
            return super.notEqual(other);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
