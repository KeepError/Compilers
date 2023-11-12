package SyntaxAnalysis;

import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Program;

import java.util.List;

public class SyntaxAnalyser {
    public static Program analyse(List<Token> tokens) throws SyntaxAnalyserError {
        return Program.findInRange(tokens, 0, tokens.size() - 1);
    }
}
