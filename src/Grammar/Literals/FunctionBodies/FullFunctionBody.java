package Grammar.Literals.FunctionBodies;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.KeywordToken;
import Tokens.Token;
import Grammar.Body;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class FullFunctionBody extends FunctionBody {
    Body body;

    public FullFunctionBody(int startToken, int tokensCount, Body body) {
        super(startToken, tokensCount);
        this.body = body;
    }

    public static FullFunctionBody findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("is"))) {
            return null;
        }
        currentToken++;
        Body body = Body.findNext(tokens, currentToken);
        if (body == null) return null;
        currentToken += body.getTokensCount();
        if (currentToken >= tokens.size()) return null;
        token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("end"))) {
            return null;
        }
        currentToken++;
        return new FullFunctionBody(startToken, currentToken - startToken, body);
    }

    @Override
    public Value execute(SymbolTable symbolTable) throws SymbolsError {
        return body.execute(symbolTable);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        body.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("body", body);
        return fields;
    }
}
