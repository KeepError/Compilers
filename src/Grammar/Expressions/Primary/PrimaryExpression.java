package Grammar.Expressions.Primary;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;
import Grammar.Grammar;
import Grammar.SyntaxError;

import java.util.List;

public abstract class PrimaryExpression extends Grammar {
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

    public abstract Value evaluate(SymbolTable symbolTable) throws SymbolsError;
}
