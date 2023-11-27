package Grammar.Literals;

import Grammar.Expressions.Expression;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolValue;
import Symbols.SymbolsError;
import Symbols.Values.ArrayValue;
import Symbols.Values.Value;
import Tokens.SeparatorToken;
import Tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArrayLiteral extends Literal {
    List<Expression> elements;

    public ArrayLiteral(int startToken, int tokensCount, List<Expression> elements) {
        super(startToken, tokensCount);
        this.elements = elements;
    }

    public static ArrayLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof SeparatorToken)) return null;
        if (!((SeparatorToken) token).getSeparator().equals("[")) return null;
        List<Expression> elements = new ArrayList<>();
        int currentToken = startToken + 1;
        String separator;
        if (currentToken + 1 >= tokens.size()) return null;
        token = tokens.get(currentToken);
        if (!((token instanceof SeparatorToken) && ((SeparatorToken) token).getSeparator().equals("]"))) {
            do {
                Expression element = Expression.findNext(tokens, currentToken);
                if (element == null) return null;
                elements.add(element);
                currentToken += element.getTokensCount();
                if (currentToken >= tokens.size()) return null;
                token = tokens.get(currentToken);
                if (!(token instanceof SeparatorToken)) return null;
                separator = ((SeparatorToken) token).getSeparator();
                if (!(separator.equals(",") || (separator.equals("]")))) {
                    return null;
                }
                currentToken++;
            } while (!separator.equals("]"));
            currentToken--;
        }
        return new ArrayLiteral(startToken, currentToken - startToken + 1, elements);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        for (Expression element : this.elements) {
            element.analyse(symbolTable);
        }
    }

    @Override
    public Value getValue(SymbolTable symbolTable) throws SymbolsError {
        List<SymbolValue> elements = new ArrayList<>();
        for (Expression element : this.elements) {
            Value value = element.evaluate(symbolTable);
            elements.add(new SymbolValue(value));
        }
        return new ArrayValue(elements);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("elements", elements);
        return fields;
    }
}
