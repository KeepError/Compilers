package Tokens;

import java.util.*;

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

        List<Integer> lengths = new ArrayList<>();
        for (String separator : SEPARATORS) {
            if (lengths.contains(separator.length())) {
                continue;
            }
            lengths.add(separator.length());
        }
        lengths.sort(Collections.reverseOrder());

        for (int length : lengths) {
            if (length > lineText.length() - startColumn) {
                continue;
            }
            String substring = lineText.substring(startColumn, startColumn + length);
            if (SEPARATORS.contains(substring)) {
                return new SeparatorToken(line, startColumn, substring.length(), substring);
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
