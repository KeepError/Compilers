package LexicalAnalysis.Tokens;

import JSON.JSONConvertable;

import java.util.*;

abstract public class TokenBase implements JSONConvertable {
    private final int line;
    private final int startColumn;
    private final int endColumn;

    public TokenBase(int line, int startColumn, int endColumn) {
        this.line = line;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    public int getLine() {
        return this.line;
    }

    public int getStartColumn() {
        return this.startColumn;
    }

    public int getEndColumn() {
        return this.endColumn;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("line", getLine());
        fields.put("startColumn", getStartColumn());
        fields.put("endColumn", getEndColumn());
        return fields;
    }
}
