package Lexer;

import Lexer.Tokens.*;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyser {
    public static List<Token> analyse(List<String> lines) throws LexicalAnalyserError {
        List<Token> tokens = new ArrayList<>();

        int currentLine = 0;
        int currentColumn = 0;
        lines.replaceAll(s -> s + " ");
        while (currentLine < lines.size()) {
            while (currentColumn < lines.get(currentLine).length() && Character.isWhitespace(lines.get(currentLine).charAt(currentColumn))) {
                currentColumn++;
            }
            if (currentColumn >= lines.get(currentLine).length()) {
                currentLine++;
                currentColumn = 0;
                continue;
            }
            Token token = parseNextToken(lines, currentLine, currentColumn);
            if (token == null) {
                throw new LexicalAnalyserError("Unexpected token at line " + (currentLine + 1) + ", column " + (currentColumn + 1) + ".");
            }
            tokens.add(token);
            currentColumn += token.getLength();
        }

        return tokens;
    }

    private static Token parseNextToken(List<String> lines, int line, int startColumn) {
        Token result;
        result = KeywordToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = SeparatorToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = OperatorToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = FloatToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = IntegerToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = IdentifierToken.parse(lines, line, startColumn);
        if (result != null) return result;
        result = StringToken.parse(lines, line, startColumn);
        return result;
    }
}
