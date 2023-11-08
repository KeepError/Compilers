package LexicalAnalysis.Tokens;

import java.util.Map;

public class IdentifierToken extends TokenBase {
    private final String identifier;

    public IdentifierToken(int line, int startColumn, int endColumn, String identifier) {
        super(line, startColumn, endColumn);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("identifier", getIdentifier());
        return fields;
    }
}
