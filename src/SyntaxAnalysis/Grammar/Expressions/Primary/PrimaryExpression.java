package SyntaxAnalysis.Grammar.Expressions.Primary;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class PrimaryExpression extends Expression {
    public PrimaryExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static PrimaryExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        PrimaryExpression primaryExpression;
        primaryExpression = PrimaryLiteralExpression.findNext(tokens, startToken);
        if (primaryExpression != null) return primaryExpression;
        primaryExpression = PrimaryBracketedExpression.findNext(tokens, startToken);
        return primaryExpression;
    }

    public static PrimaryExpression findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        PrimaryExpression primaryExpression;
        primaryExpression = PrimaryLiteralExpression.findInRange(tokens, startToken, endToken);
        if (primaryExpression != null) return primaryExpression;
        primaryExpression = PrimaryBracketedExpression.findInRange(tokens, startToken, endToken);
        return primaryExpression;
    }
}
