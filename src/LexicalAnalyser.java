import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LexicalAnalyser {
    static final Set<String> keywords = new HashSet<>(Set.of("var", "int", "real",
            "string", "bool", "while", "for", "loop", "if", "then", "else", "end",
            "print", "empty", "func", "not", "true", "false", "is", "return", "range", "in",
            "readInt", "readString", "readReal"));
    static final Set<String> operators = new HashSet<>(Set.of("+", "-", "/", "*", ".", ":=", ">", "=",
            "<", ">=", "<=", "&&", "||", ":"));
    static final Set<String> separators = new HashSet<>(Set.of(".", ";", "{", "}", "(", ")", "[", "]", ","));

    public static List<Token> analyze(List<String> input) {
        String line = preprocess(input);
        // System.out.println("Preprocessed input:\n " + line);
        List<Token> tokens = new ArrayList<>();
        int pos = 0;

        while (pos < line.length()) {
            char ch = line.charAt(pos);
            if (ch == ' ') {
                pos++;
            } else if (separators.contains(ch + "")) {
                tokens.add(new Token(TokenType.SEPARATOR, 7, ch + "", pos, pos + 1));
                pos++;
            } else if (operators.contains(ch + "")) {
                if (pos + 1 < line.length() && operators.contains(ch + "" + line.charAt(pos + 1))) {
                    tokens.add(new Token(TokenType.OPERATOR, 5, ch + "" + line.charAt(pos + 1), pos, pos + 2));
                    pos += 2;
                } else {
                    tokens.add(new Token(TokenType.OPERATOR, 5, ch + "", pos, pos + 1));
                    pos++;
                }
            } else if (isDigit(ch)) {
                int start = pos;
                while (pos < line.length() && (isDigit(line.charAt(pos)) || line.charAt(pos) == '.')) {
                    pos++;
                }

                String temp = line.substring(start, pos);
                if (temp.contains(".")) {
                    tokens.add(new Token(TokenType.DOT, 3, temp, start, pos));
                } else {
                    tokens.add(new Token(TokenType.DIGIT, 2, temp, start, pos));
                }
            } else if (ch == '\'' || ch == '"') {
                int start = pos + 1;
                pos++;
                while (pos < line.length() && line.charAt(pos) != ch) {
                    pos++;
                }
                tokens.add(new Token(TokenType.STRING, 4, line.substring(start - 1, pos + 1), start, pos));
                pos++;
            } else if (isLetter(ch)) {
                int start = pos;
                while (pos < line.length() && (isDigit(line.charAt(pos)) || isLetter(line.charAt(pos)))) {
                    pos++;
                }
                String temp = line.substring(start, pos);
                if (keywords.contains(temp)) {
                    tokens.add(new Token(TokenType.KEYWORD, 6, temp, start, pos));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, 1, temp, start, pos));
                }
            }
        }

        for (Token token : tokens) {
            System.out.println(token);
        }

        return tokens;
    }

    private static String preprocess(List<String> input) {
        StringBuilder result = new StringBuilder();
        for (String line : input) {
            // Check for the presence of '\n' in each line
            if (line.contains("\n")) {
                // Handle cases where '\n' is found
                String[] lines = line.split("\n");
                for (int i = 0; i < lines.length; i++) {
                    // Trim and append each line separately
                    String trimmedLine = lines[i].trim();
                    if (!trimmedLine.equals("")) {
                        result.append(trimmedLine);
                        // Add a space if it's not the last line
                        if (i < lines.length - 1) {
                            result.append(" ");
                        }
                    }
                }
            } else {
                // No '\n' in the line, so just trim and append it
                line = line.trim();
                if (!line.equals("")) {
                    result.append(line).append(" ");
                }
            }
        }
        return result.toString();
    }

    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static void tokensToJSON(List<String> input) {

        StringBuilder json = new StringBuilder("[");
        for (Token token : analyze(input)) {
            if (json.length() > 1) {
                json.append(",");
            }
            json.append(token.toJSON());
        }
        json.append("]");

        String fileName = "output.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json.toString());
            System.out.println("JSON data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while saving JSON data to " + fileName);
        }
    }

}
