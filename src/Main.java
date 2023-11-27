import Analysis.Lexical.LexicalAnalyser;
import Analysis.Lexical.LexicalAnalyserError;
import Analysis.Semantic.SemanticAnalyser;
import Analysis.Syntax.SyntaxAnalyser;
import Grammar.Program;
import Grammar.SyntaxError;
import Interpretation.Interpreter;
import JSON.JSONSerializer;
import Symbols.SymbolsError;
import Tokens.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> readFromFile(String fileName) throws IOException {
        List<String> inputLines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            inputLines.add(line);
        }
        return inputLines;
    }

    public static void main(String[] args) throws IOException, LexicalAnalyserError, SyntaxError, SymbolsError {
        List<String> inputLines = readFromFile("test.ddd");
        List<Token> tokens = LexicalAnalyser.analyse(inputLines);
        System.out.println(JSONSerializer.serialize(tokens));
        Program program = SyntaxAnalyser.analyse(tokens);
        System.out.println(JSONSerializer.serialize(program));
        SemanticAnalyser.analyse(program);
        System.out.println(JSONSerializer.serialize(program));
        System.out.println("Interpreting...");
        Interpreter.interpret(program);
    }
}