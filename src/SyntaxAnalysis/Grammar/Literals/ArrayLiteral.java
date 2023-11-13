package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

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
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("elements", elements);
        return fields;
    }
}
