package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class BooleanLiteral extends Literal {
    boolean booleanValue;

    public BooleanLiteral(int startToken, int tokensCount, boolean booleanValue) {
        super(startToken, tokensCount);
        this.booleanValue = booleanValue;
    }

    public static BooleanLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof KeywordToken) {
            String keyword = ((KeywordToken) token).getKeyword();
            if (keyword.equals("true")) {
                return new BooleanLiteral(startToken, 1, true);
            } else if (keyword.equals("false")) {
                return new BooleanLiteral(startToken, 1, false);
            }
        }
        return null;
    }

    public static BooleanLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        BooleanLiteral booleanLiteral = findNext(tokens, startToken);
        if (booleanLiteral == null || booleanLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return booleanLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("booleanValue", booleanValue);
        return fields;
    }
}
