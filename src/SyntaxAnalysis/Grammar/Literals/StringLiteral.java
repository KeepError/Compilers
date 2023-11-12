package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.StringToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class StringLiteral extends Literal {
    String stringValue;

    public StringLiteral(int startToken, int tokensCount, String stringValue) {
        super(startToken, tokensCount);
        this.stringValue = stringValue;
    }

    public static StringLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof StringToken) {
            return new StringLiteral(startToken, 1, ((StringToken) token).getStringValue());
        }
        return null;
    }

    public static StringLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        StringLiteral stringLiteral = findNext(tokens, startToken);
        if (stringLiteral == null || stringLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return stringLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("stringValue", stringValue);
        return fields;
    }
}
