package SyntaxAnalysis.Grammar;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Statements.Statement;

import java.util.ArrayList;
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
        return new Program(startToken, body.getTokensCount(), body);
    }

    public static Program findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        Program program = findNext(tokens, startToken);
        if (program == null || program.getTokensCount() != endToken - startToken + 1) return null;
        return program;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("body", body);
        return fields;
    }
}
