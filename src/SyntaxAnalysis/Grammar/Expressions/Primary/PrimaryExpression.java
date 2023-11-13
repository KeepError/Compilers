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
        if (primaryExpression != null) return primaryExpression;
        primaryExpression = PrimaryReservedExpression.findNext(tokens, startToken);
        return primaryExpression;
    }
}
