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

    @Override
    public Map<String, Object> getJSONFields() {
        return super.getJSONFields();
    }
}
