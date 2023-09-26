import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> readFromFile(String fileName){
        List<String> inputLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  inputLines;
    }
    public static void main(String[] args) {

        String fileName = "src/test.ddd";
        LexicalAnalyser.tokensToJSON(readFromFile(fileName));

    }
}