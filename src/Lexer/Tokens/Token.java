package Lexer.Tokens;

import JSON.JSONSerializable;

import java.util.*;

abstract public class Token implements JSONSerializable {
    private final int line;
    private final int column;
    private final int length;

    public Token(int line, int startColumn, int endColumn) {
        this.line = line;
        this.column = startColumn;
        this.length = endColumn;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("line", getLine());
        fields.put("column", getColumn());
        fields.put("length", getLength());
        return fields;
    }
}
