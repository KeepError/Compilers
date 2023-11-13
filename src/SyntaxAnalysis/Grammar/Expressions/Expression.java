package SyntaxAnalysis.Grammar.Expressions;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expressions.FullExpression;
import SyntaxAnalysis.Grammar.Expressions.Expressions.MinimalExpression;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class Expression extends Grammar {
    public Expression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Expression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Expression expression;
        expression = MinimalExpression.findNext(tokens, startToken);
        if (expression != null) return findNext(tokens, startToken, expression);
        return null;
    }

    public static Expression findNext(List<Token> tokens, int startToken, Expression term) throws SyntaxError {
        Expression expression;
        expression = FullExpression.findNext(tokens, startToken, term);
        if (expression != null) return findNext(tokens, startToken, expression);
        return term;
    }
}
