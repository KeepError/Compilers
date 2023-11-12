package SyntaxAnalysis.Grammar.Literals;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class EmptyLiteral extends Literal {
    public EmptyLiteral(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static EmptyLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (token instanceof KeywordToken) {
            String keyword = ((KeywordToken) token).getKeyword();
            if (keyword.equals("empty")) {
                return new EmptyLiteral(startToken, 1);
            } else {
                return null;
            }
        }
        return null;
    }

    public static EmptyLiteral findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        EmptyLiteral emptyLiteral = findNext(tokens, startToken);
        if (emptyLiteral == null || emptyLiteral.getTokensCount() != endToken - startToken + 1) return null;
        return emptyLiteral;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        return fields;
    }
}
