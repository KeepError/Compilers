import JSON.JSONSerializer;
import LexicalAnalysis.LexicalAnalyser;
import LexicalAnalysis.LexicalAnalyserError;
import LexicalAnalysis.Tokens.Token;

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

    public static void main(String[] args) throws IOException, LexicalAnalyserError {
        List<String> inputLines = readFromFile("test.ddd");
        List<Token> tokens = LexicalAnalyser.analyse(inputLines);
        System.out.println(JSONSerializer.serialize(tokens));
    }
}