package SyntaxAnalysis.Grammar.Expressions.Factor;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class FactorExpression extends Grammar {
    public FactorExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static FactorExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        FactorExpression factorExpression;
        factorExpression = MinimalFactorExpression.findNext(tokens, startToken);
        if (factorExpression != null) return findNext(tokens, startToken, factorExpression);
        return null;
    }

    public static FactorExpression findNext(List<Token> tokens, int startToken, FactorExpression factor) throws SyntaxError {
        FactorExpression factorExpression;
        factorExpression = FullFactorExpression.findNext(tokens, startToken, factor);
        if (factorExpression != null) return findNext(tokens, startToken, factorExpression);
        return factor;
    }
}
