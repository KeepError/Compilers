package SyntaxAnalysis.Grammar;

import LexicalAnalysis.Tokens.Token;

import java.util.List;
import java.util.Map;

public class Program extends Grammar {
    Body body;

    public Program(int startToken, int tokensCount, Body body) {
        super(startToken, tokensCount);
        this.body = body;
    }

    public static Program findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Body body = Body.findNext(tokens, startToken);
        if (body == null) return null;
        if (body.getTokensCount() != tokens.size()) return null;
        return new Program(startToken, body.getTokensCount(), body);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("body", body);
        return fields;
    }
}
