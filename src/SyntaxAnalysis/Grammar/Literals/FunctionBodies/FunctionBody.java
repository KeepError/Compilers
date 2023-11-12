package SyntaxAnalysis.Grammar.Literals.FunctionBodies;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Grammar;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;

public abstract class FunctionBody extends Grammar {
    public FunctionBody(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static FunctionBody findNext(List<Token> tokens, int startToken) throws SyntaxError {
        FunctionBody functionBody;
        functionBody = ArrowFunctionBody.findNext(tokens, startToken);
        if (functionBody != null) return functionBody;
        functionBody = FullFunctionBody.findNext(tokens, startToken);
        return functionBody;
    }
}
