import Grammar.SyntaxError;
import JSON.JSONSerializer;
import Analysis.Lexical.LexicalAnalyser;
import Analysis.Lexical.LexicalAnalyserError;
import Tokens.Token;
import Grammar.Program;

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

    public static void main(String[] args) throws IOException, LexicalAnalyserError, SyntaxError {
        List<String> inputLines = readFromFile("test.ddd");
        List<Token> tokens = LexicalAnalyser.analyse(inputLines);
        Program program = Analysis.Syntax.SyntaxAnalyser.analyse(tokens);
        System.out.println(JSONSerializer.serialize(tokens));
        System.out.println(JSONSerializer.serialize(program));
    }
}