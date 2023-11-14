package Analysis.Syntax;

import Grammar.SyntaxError;
import Tokens.Token;
import Grammar.Program;

import java.util.List;

public class SyntaxAnalyser {
    public static Program analyse(List<Token> tokens) throws SyntaxError {
        return Program.findNext(tokens, 0);
    }
}
