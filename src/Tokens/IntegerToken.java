package Tokens;

import java.util.List;
import java.util.Map;

public class IntegerToken extends Token {
    private final int integerValue;

    public IntegerToken(int line, int startColumn, int endColumn, int integerValue) {
        super(line, startColumn, endColumn);
        this.integerValue = integerValue;
    }

    public int getIntegerValue() {
        return this.integerValue;
    }

    public static IntegerToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        if (Character.isDigit(lineText.charAt(startColumn))) {
            int endColumn = startColumn;
            while (endColumn < lineText.length() && Character.isDigit(lineText.charAt(endColumn + 1))) {
                endColumn++;
            }
            if (Character.isLetter(lineText.charAt(endColumn + 1))) return null;
            return new IntegerToken(line, startColumn, (endColumn - startColumn + 1), Integer.parseInt(lineText.substring(startColumn, endColumn + 1)));
        }
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("integerValue", getIntegerValue());
        return fields;
    }
}
