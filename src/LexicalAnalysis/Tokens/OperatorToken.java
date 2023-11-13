package LexicalAnalysis.Tokens;

import java.util.*;

public class OperatorToken extends Token {
    static final Set<String> OPERATORS = Set.of("+", "-", "/", "*", ":=", ">", "=", "<", ">=", "<=", "/=", "and", "or", "xor", "not");

    private final String operator;

    public OperatorToken(int line, int startColumn, int endColumn, String operator) {
        super(line, startColumn, endColumn);
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }

    public static OperatorToken parse(List<String> lines, int line, int startColumn) {
        String lineText = lines.get(line);

        List<Integer> lengths = new ArrayList<>();
        for (String operator : OPERATORS) {
            if (lengths.contains(operator.length())) {
                continue;
            }
            lengths.add(operator.length());
        }
        lengths.sort(Collections.reverseOrder());

        for (int length : lengths) {
            if (length > lineText.length() - startColumn) {
                continue;
            }
            String substring = lineText.substring(startColumn, startColumn + length);
            if (OPERATORS.contains(substring)) {
                return new OperatorToken(line, startColumn, substring.length(), substring);
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("operator", getOperator());
        return fields;
    }
}
