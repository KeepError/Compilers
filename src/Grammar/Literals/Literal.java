package Grammar.Literals;

import Tokens.Token;
import Grammar.Grammar;
import Grammar.SyntaxError;

import java.util.List;

public abstract class Literal extends Grammar {
    public Literal(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Literal findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Literal literal;
        literal = IntegerLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = RealLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = StringLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = BooleanLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = EmptyLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = ArrayLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = TupleLiteral.findNext(tokens, startToken);
        if (literal != null) return literal;
        literal = FunctionLiteral.findNext(tokens, startToken);
        return literal;
    }
}
