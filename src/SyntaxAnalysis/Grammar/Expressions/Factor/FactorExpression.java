package SyntaxAnalysis.Grammar.Expressions.Factor;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class FactorExpression extends Expression {
    public FactorExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static FactorExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        FactorExpression termExpression;
        termExpression = MinimalFactorExpression.findNext(tokens, startToken);
        if (termExpression != null) return findNext(tokens, startToken, termExpression);
        return null;
    }

    public static FactorExpression findNext(List<Token> tokens, int startToken, FactorExpression factor) throws SyntaxError {
        FactorExpression termExpression;
        termExpression = FullFactorExpression.findNext(tokens, startToken, factor);
        if (termExpression != null) return findNext(tokens, startToken, termExpression);
        return factor;
    }
}
