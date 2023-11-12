package SyntaxAnalysis.Grammar.Expressions;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class Expression extends Grammar {
    public Expression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Expression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Expression expression;
        expression = UnaryExpression.findNext(tokens, startToken);
        if (expression != null) return expression;
        expression = PrimaryExpression.findNext(tokens, startToken);
        return expression;
    }

    public static Expression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        Expression expression;
        expression = UnaryExpression.findInRange(tokens, startToken, endToken);
        if (expression != null) return expression;
        expression = PrimaryExpression.findInRange(tokens, startToken, endToken);
        return expression;
    }
}
