package Grammar.Literals.FunctionBodies;

import Grammar.Grammar;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;

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

    public abstract Value execute(SymbolTable symbolTable) throws SymbolsError;
}
