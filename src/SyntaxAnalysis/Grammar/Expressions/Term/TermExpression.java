package SyntaxAnalysis.Grammar.Expressions.Term;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class TermExpression extends Grammar {
    public TermExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static TermExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        TermExpression termExpression;
        termExpression = MinimalTermExpression.findNext(tokens, startToken);
        if (termExpression != null) return findNext(tokens, startToken, termExpression);
        return null;
    }

    public static TermExpression findNext(List<Token> tokens, int startToken, TermExpression term) throws SyntaxError {
        TermExpression termExpression;
        termExpression = FullTermExpression.findNext(tokens, startToken, term);
        if (termExpression != null) return findNext(tokens, startToken, termExpression);
        return term;
    }
}
