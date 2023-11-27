package Grammar.Literals;

import Symbols.SymbolTable;
import Symbols.SymbolValue;
import Symbols.SymbolsError;
import Symbols.Values.TupleValue;
import Symbols.Values.Value;
import Tokens.SeparatorToken;
import Tokens.Token;
import Grammar.SyntaxError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TupleLiteral extends Literal {
    List<TupleElement> elements;

    public TupleLiteral(int startToken, int tokensCount, List<TupleElement> elements) {
        super(startToken, tokensCount);
        this.elements = elements;
    }

    public static TupleLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof SeparatorToken)) return null;
        if (!((SeparatorToken) token).getSeparator().equals("{")) return null;
        List<TupleElement> elements = new ArrayList<>();
        int currentToken = startToken + 1;
        String separator;
        if (currentToken + 1 >= tokens.size()) return null;
        do {
            TupleElement element = TupleElement.findNext(tokens, currentToken);
            if (element == null) return null;
            elements.add(element);
            currentToken += element.getTokensCount();
            if (currentToken >= tokens.size()) return null;
            token = tokens.get(currentToken);
            if (!(token instanceof SeparatorToken)) return null;
            separator = ((SeparatorToken) token).getSeparator();
            if (!(separator.equals(",") || (separator.equals("}")))) {
                return null;
            }
            currentToken++;
        } while (!separator.equals("}"));
        currentToken--;
        return new TupleLiteral(startToken, currentToken - startToken + 1, elements);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        for (TupleElement element : this.elements) {
            element.analyse(symbolTable);
        }
    }

    @Override
    public Value getValue(SymbolTable symbolTable) throws SymbolsError {
        Map<String, SymbolValue> namedElements = new HashMap<>();
        Map<Integer, SymbolValue> unnamedElements = new HashMap<>();
        Integer index = 0;
        for (TupleElement tupleElement : elements) {
            index++;
            String identifier = tupleElement.getIdentifier();
            Value value = tupleElement.getValue().evaluate(symbolTable);
            SymbolValue symbolValue = new SymbolValue(value);
            if (identifier == null) {
                unnamedElements.put(index, symbolValue);
            } else {
                namedElements.put(identifier, symbolValue);
            }
        }
        return new TupleValue(namedElements, unnamedElements);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("elements", elements);
        return fields;
    }
}
