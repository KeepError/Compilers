package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.IdentifierToken;
import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;
import SyntaxAnalysis.Helpers.Helpers;

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

    public static ArrayElementReference findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int endToken = tokens.size() - 1;
        do {
            ArrayElementReference arrayElementReference = findInRange(tokens, startToken, endToken);
            if (arrayElementReference != null) {
                return arrayElementReference;
            }
            endToken--;
        } while (endToken >= startToken);
        return null;
    }

    public static ArrayElementReference findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        Token token = tokens.get(endToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("]"))) {
            return null;
        }
        int lastBracket = endToken;
        Integer firstBracket = Helpers.searchSkippingBrackets(tokens, endToken - 1, token1 -> token1 instanceof SeparatorToken && ((SeparatorToken) token1).getSeparator().equals("["));
        if (firstBracket == null || firstBracket < startToken) {
            return null;
        }
        Expression expression = Expression.findInRange(tokens, firstBracket + 1, lastBracket - 1);
        if (expression == null) {
            return null;
        }
        Reference object = Reference.findInRange(tokens, startToken, firstBracket - 1);
        if (object == null) {
            return null;
        }
        return new ArrayElementReference(startToken, endToken - startToken + 1, object, expression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("arrayElement", arrayElement);
        return fields;
    }
}
