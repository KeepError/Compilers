package SyntaxAnalysis.Grammar;

import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program extends Grammar {
    List<Statement> statements;

    public Program(int startToken, int tokensCount, List<Statement> statements) {
        super(startToken, tokensCount);
        this.statements = statements;
    }

    public static Program findNext(List<Token> tokens, int startToken) throws SyntaxError {
        List<Statement> statements = new ArrayList<>();
        int currentToken = startToken;
        while (currentToken < tokens.size()) {
            Statement statement = Statement.findNext(tokens, currentToken);
            if (statement == null) {
                return null;
            }
            currentToken += statement.getTokensCount();
            if (currentToken >= tokens.size()) {
                return null;
            }
            Token token = tokens.get(currentToken);
            if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(";")) {
                currentToken++;
            } else {
                return null;
            }
            statements.add(statement);
        }
        return new Program(startToken, currentToken - startToken, statements);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("statements", statements);
        return fields;
    }
}
