package Grammar.References;

import Grammar.Expressions.Expression;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolValue;
import Symbols.SymbolsError;
import Symbols.Values.ArrayValue;
import Symbols.Values.IntegerValue;
import Symbols.Values.Value;
import Tokens.SeparatorToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class ArrayElementReference extends Reference {
    Reference object;
    Expression arrayElement;

    public ArrayElementReference(int startToken, int tokensCount, Reference object, Expression arrayElement) {
        super(startToken, tokensCount);
        this.object = object;
        this.arrayElement = arrayElement;
    }

    public static ArrayElementReference findNext(List<Token> tokens, int startToken, Reference object) throws SyntaxError {
        int currentToken = startToken + object.getTokensCount();
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("["))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        Expression expression = Expression.findNext(tokens, currentToken);
        if (expression == null) {
            return null;
        }
        currentToken += expression.getTokensCount();
        token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("]"))) {
            return null;
        }
        currentToken++;
        return new ArrayElementReference(startToken, currentToken - startToken, object, expression);
    }

    @Override
    public SymbolValue getSymbolValue(SymbolTable symbolTable) throws SymbolsError {
        SymbolValue objectValue = object.getSymbolValue(symbolTable);
        if (objectValue.getValue() instanceof ArrayValue arrayValue) {
            Value indexValue = arrayElement.evaluate(symbolTable);
            int index;
            if (indexValue instanceof IntegerValue integerValue) {
                index = integerValue.getValue();
            } else {
                throw new SymbolsError("Array index must be an integer.");
            }
            if (index < 1 || index > arrayValue.getElements().size()) {
                throw new SymbolsError("Array index out of bounds.");
            }
            return arrayValue.getElements().get(index - 1);
        }
        throw new SymbolsError("Not an array.");
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        object.analyse(symbolTable);
        arrayElement.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("arrayElement", arrayElement);
        return fields;
    }
}
