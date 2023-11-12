package SyntaxAnalysis.Grammar.Expressions.Primary;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class PrimaryReservedExpression extends PrimaryExpression {
    private final String function;

    public PrimaryReservedExpression(int startToken, int tokensCount, String function) {
        super(startToken, tokensCount);
        this.function = function;
    }

    public static PrimaryReservedExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) {
            return null;
        }
        String keyword = ((KeywordToken) token).getKeyword();
        if (!keyword.equals("readInt") && !keyword.equals("readReal") && !keyword.equals("readString")) {
            return null;
        }
        return new PrimaryReservedExpression(startToken, 1, keyword);
    }

    public static PrimaryReservedExpression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        PrimaryReservedExpression primaryExpression = findNext(tokens, startToken);
        if (primaryExpression == null || primaryExpression.getTokensCount() != endToken - startToken + 1) return null;
        return primaryExpression;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("function", function);
        return fields;
    }
}
