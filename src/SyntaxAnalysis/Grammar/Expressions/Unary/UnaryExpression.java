package SyntaxAnalysis.Grammar.Expressions.Unary;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public class UnaryExpression extends Grammar {
    public UnaryExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static UnaryExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        UnaryExpression unaryExpression;
        unaryExpression = UnaryReferenceTypeIndicatorExpression.findNext(tokens, startToken);
        if (unaryExpression != null) return unaryExpression;
        unaryExpression = UnaryReferenceExpression.findNext(tokens, startToken);
        if (unaryExpression != null) return unaryExpression;
        unaryExpression = UnaryPrimaryExpression.findNext(tokens, startToken);
        return unaryExpression;
    }
}
