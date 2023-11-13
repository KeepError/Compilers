package SyntaxAnalysis.Grammar.TypeIndicators;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class TypeIndicator extends Grammar {
    public TypeIndicator(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static TypeIndicator findNext(List<Token> tokens, int startToken) throws SyntaxError {
        TypeIndicator typeIndicator;
        typeIndicator = IntegerTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = RealTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = BooleanTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = StringTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = EmptyTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = ArrayTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = TupleTypeIndicator.findNext(tokens, startToken);
        if (typeIndicator != null) return typeIndicator;
        typeIndicator = FunctionTypeIndicator.findNext(tokens, startToken);
        return typeIndicator;
    }
}
