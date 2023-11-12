package LexicalAnalysis.Tokens;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SeparatorToken extends Token {
    private final String separator;

    static final Set<String> SEPARATORS = Set.of("..", "=>", ";", "{", "}", "(", ")", "[", "]", ",", ".");

    public SeparatorToken(int line, int startColumn, int endColumn, String separator) {
        super(line, startColumn, endColumn);
        this.separator = separator;
    }

    public String getSeparator() {
        return this.separator;
    }

    public static SeparatorToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);

        for (String separator : SEPARATORS) {
            if (lineText.startsWith(separator, startColumn)) {
                return new SeparatorToken(line, startColumn, separator.length(), separator);
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("separator", getSeparator());
        return fields;
    }
}
