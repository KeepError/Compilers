package Tokens;

import java.util.List;
import java.util.Map;

public class RealToken extends Token {
    private final float realValue;

    public RealToken(int line, int column, int length, float floatValue) {
        super(line, column, length);
        this.realValue = floatValue;
    }

    public float getRealValue() {
        return this.realValue;
    }

    public static RealToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        if (!Character.isDigit(lineText.charAt(startColumn))) return null;
        int endColumn = startColumn + 1;
        while (Character.isDigit(lineText.charAt(endColumn))) {
            endColumn++;
        }
        if (lineText.charAt(endColumn) != '.') return null;
        endColumn++;
        int columnAfterDot = endColumn;
        while (Character.isDigit(lineText.charAt(endColumn))) {
            endColumn++;
        }
        if (endColumn == columnAfterDot) return null;
        if (Character.isLetter(lineText.charAt(endColumn + 1)) || lineText.charAt(endColumn + 1) == '.') return null;
        return new RealToken(line, startColumn, (endColumn - startColumn), Float.parseFloat(lineText.substring(startColumn, endColumn)));
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("realValue", getRealValue());
        return fields;
    }
}
