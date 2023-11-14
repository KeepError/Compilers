package Grammar.Expressions.Primary;

import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.SyntaxError;

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

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("function", function);
        return fields;
    }
}
