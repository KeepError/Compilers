package Grammar.Expressions.Factor;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;
import Grammar.Grammar;
import Grammar.SyntaxError;

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

    public abstract Value evaluate(SymbolTable symbolTable) throws SymbolsError;
}
