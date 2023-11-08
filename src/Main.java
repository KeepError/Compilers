import JSON.JSONConverter;
import LexicalAnalysis.Tokens.IdentifierToken;

public class Main {
    public static void main(String[] args) {
        IdentifierToken token = new IdentifierToken(1, 2, 3, "hi");
        String json = JSONConverter.convert(token);
        System.out.println(json);
    }
}