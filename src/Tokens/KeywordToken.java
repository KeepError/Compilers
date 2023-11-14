package Tokens;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeywordToken extends Token {
    private final String keyword;

    static final Set<String> KEYWORDS = Set.of("var", "int", "real", "string", "bool", "while", "for", "loop", "if", "then", "else", "end", "print", "empty", "func", "true", "false", "is", "return", "in", "readInt", "readReal", "readString");

    public KeywordToken(int line, int startColumn, int endColumn, String keyword) {
        super(line, startColumn, endColumn);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public static KeywordToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        for (String keyword : KEYWORDS) {
            if (!lineText.startsWith(keyword, startColumn)) continue;
            int length = keyword.length();
            if (Character.isLetterOrDigit(lineText.charAt(startColumn + length))) continue;
            return new KeywordToken(line, startColumn, length, keyword);
        }
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("keyword", getKeyword());
        return fields;
    }
}
