package Tokens;

import java.util.List;
import java.util.Map;

public class StringToken extends Token {
    private final String stringValue;

    public StringToken(int line, int startColumn, int endColumn, String stringValue) {
        super(line, startColumn, endColumn);
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public static StringToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        if (lineText.charAt(startColumn) == '"') {
            int endColumn = startColumn + 1;
            while (endColumn < lineText.length() && lineText.charAt(endColumn) != '"') {
                endColumn++;
            }
            if (endColumn < lineText.length() && lineText.charAt(endColumn) == '"') {
                endColumn++;
                return new StringToken(line, startColumn, (endColumn - startColumn), lineText.substring(startColumn + 1, endColumn - 1));
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("stringValue", getStringValue());
        return fields;
    }
}
