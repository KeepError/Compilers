package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

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

    public static FunctionCallReference findNext(List<Token> tokens, int startToken, Reference object) throws SyntaxError {
        int currentToken = startToken + object.getTokensCount();
        Token token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("("))) {
            return null;
        }
        currentToken++;
        if (currentToken >= tokens.size()) return null;
        List<Expression> parameters = new ArrayList<>();
        int separatorCount = 0;
        String separator = "";
        token = tokens.get(currentToken);
        if (!(token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(")"))) {
            do {
                token = tokens.get(currentToken);
                if (separatorCount == parameters.size()) {
                    Expression expression = Expression.findNext(tokens, currentToken);
                    if (expression == null) return null;
                    parameters.add(expression);
                    currentToken += expression.getTokensCount();
                } else if (separatorCount == parameters.size() - 1) {
                    if (!(token instanceof SeparatorToken)) return null;
                    separator = ((SeparatorToken) token).getSeparator();
                    if (!(separator.equals(",") || separator.equals(")"))) return null;
                    separatorCount++;
                    currentToken++;
                } else {
                    return null;
                }
            } while (currentToken <= tokens.size() && !(separator.equals(")")));
        } else {
            currentToken++;
        }
        if (separatorCount != parameters.size()) return null;
        return new FunctionCallReference(startToken, currentToken - startToken, object, parameters);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("object", object);
        fields.put("parameters", parameters);
        return fields;
    }
}
