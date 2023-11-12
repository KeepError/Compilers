package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class Statement extends Grammar {
    public Statement(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Statement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Statement statement = ReturnStatement.findNext(tokens, startToken);
        return statement;
    }
}
