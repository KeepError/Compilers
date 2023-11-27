package Symbols.Values;

import Grammar.Literals.FunctionBodies.FunctionBody;

import java.util.List;

public class FunctionValue extends Value {
    List<String> parameters;
    FunctionBody functionBody;

    public FunctionValue(List<String> parameters, FunctionBody functionBody) {
        this.parameters = parameters;
        this.functionBody = functionBody;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public FunctionBody getFunctionBody() {
        return functionBody;
    }

    @Override
    public String toString() {
        return "func<" + String.join(", ", parameters) + ">";
    }
}
