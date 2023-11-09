package Lexer.Tokens;

import java.util.List;
import java.util.Map;

public class FloatToken extends Token {
    private final float floatValue;

    public FloatToken(int line, int column, int length, float floatValue) {
        super(line, column, length);
        this.floatValue = floatValue;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public static FloatToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        if (!Character.isDigit(lineText.charAt(startColumn))) return null;
        int endColumn = startColumn + 1;
        while (Character.isDigit(lineText.charAt(endColumn))) {
            endColumn++;
        }
        if (lineText.charAt(endColumn) != '.') return null;
        endColumn++;
        while (Character.isDigit(lineText.charAt(endColumn))) {
            endColumn++;
        }
        if (Character.isLetter(lineText.charAt(endColumn + 1)) || lineText.charAt(endColumn + 1) == '.') return null;
        return new FloatToken(line, startColumn, (endColumn - startColumn), Float.parseFloat(lineText.substring(startColumn, endColumn)));
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("floatValue", getFloatValue());
        return fields;
    }
}
