package SyntaxAnalysis.Grammar.Expressions;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Factor.FactorExpression;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class Expression extends Grammar {
    public Expression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Expression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Expression expression;
        expression = FactorExpression.findNext(tokens, startToken);
        return expression;
    }
}
