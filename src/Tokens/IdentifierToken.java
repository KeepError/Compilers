package Tokens;

import java.util.List;
import java.util.Map;

public class IdentifierToken extends Token {
    private final String identifier;

    public IdentifierToken(int line, int startColumn, int endColumn, String identifier) {
        super(line, startColumn, endColumn);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public static IdentifierToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);
        if (Character.isLetter(lineText.charAt(startColumn))) {
            int endColumn = startColumn + 1;
            while (endColumn < lineText.length() && (Character.isLetterOrDigit(lineText.charAt(endColumn)) || lineText.charAt(endColumn) == '_')) {
                endColumn++;
            }
            return new IdentifierToken(line, startColumn, (endColumn - startColumn), lineText.substring(startColumn, endColumn));
        }
        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", getIdentifier());
        return fields;
    }
}
