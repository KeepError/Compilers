package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.ArrayList;
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

    public static TupleLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        TupleLiteral tupleLiteral = findNext(tokens, startToken);
        if (tupleLiteral == null || tupleLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return tupleLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("elements", elements);
        return fields;
    }
}
