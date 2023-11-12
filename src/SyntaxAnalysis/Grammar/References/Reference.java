package SyntaxAnalysis.Grammar.References;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class Reference extends Grammar {
    public Reference(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Reference findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Reference reference;
        reference = ArrayElementReference.findNext(tokens, startToken);
        if (reference != null) return reference;
        reference = NamedTupleElementReference.findNext(tokens, startToken);
        if (reference != null) return reference;
        reference = FunctionCallReference.findNext(tokens, startToken);
        if (reference != null) return reference;
        reference = IdentifierReference.findNext(tokens, startToken);
        return reference;
    }

    public static Reference findInRange(List<Token> tokens, int startToken, int endToken) throws SyntaxError {
        Reference reference;
        reference = ArrayElementReference.findInRange(tokens, startToken, endToken);
        if (reference != null) return reference;
        reference = NamedTupleElementReference.findInRange(tokens, startToken, endToken);
        if (reference != null) return reference;
        reference = FunctionCallReference.findInRange(tokens, startToken, endToken);
        if (reference != null) return reference;
        reference = IdentifierReference.findInRange(tokens, startToken, endToken);
        return reference;
    }
}
