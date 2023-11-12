package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;
import SyntaxAnalysis.Helpers.Helpers;
import SyntaxAnalysis.Helpers.SearchSkippingBracketsChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionCallReference extends Reference {
    Reference object;
    List<Expression> parameters;

    public FunctionCallReference(int startToken, int tokensCount, Reference object, List<Expression> parameters) {
        super(startToken, tokensCount);
        this.object = object;
        this.parameters = parameters;
    }

    public static FunctionCallReference findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int endToken = tokens.size() - 1;
        do {
            FunctionCallReference functionCallReference = findInRange(tokens, startToken, endToken);
            if (functionCallReference != null) {
                return functionCallReference;
            }
            endToken--;
        } while (endToken >= startToken);
        return null;
    }

    public static FunctionCallReference findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        int lastSeparator = endToken;
        Integer currentSeparator = lastSeparator;
        if (!(tokens.get(lastSeparator) instanceof SeparatorToken && ((SeparatorToken) tokens.get(lastSeparator)).getSeparator().equals(")"))) {
            return null;
        }
        List<Expression> parameters = new ArrayList<>();
        SearchSkippingBracketsChecker checker = token -> token instanceof SeparatorToken && (((SeparatorToken) token).getSeparator().equals(",") || ((SeparatorToken) token).getSeparator().equals("("));
        Token foundToken;
        do {
            currentSeparator = Helpers.searchSkippingBrackets(tokens, currentSeparator - 1, checker);
            if (currentSeparator == null || currentSeparator < startToken) {
                return null;
            }
            foundToken = tokens.get(currentSeparator);
            if (currentSeparator != lastSeparator - 1) {
                Expression parameter = Expression.findInRange(tokens, currentSeparator + 1, lastSeparator - 1);
                if (parameter == null) {
                    return null;
                }
                parameters.add(0, parameter);
            }
            lastSeparator = currentSeparator;
        } while (!(foundToken instanceof SeparatorToken && ((SeparatorToken) foundToken).getSeparator().equals("(")));
        Reference object = Reference.findInRange(tokens, startToken, lastSeparator - 1);
        if (object == null) {
            return null;
        }
        return new FunctionCallReference(startToken, endToken - startToken + 1, object, parameters);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("parameters", parameters);
        return fields;
    }
}
