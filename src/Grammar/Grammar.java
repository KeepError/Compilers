package Grammar;

import JSON.JSONSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Grammar implements JSONSerializable {
    private final int startToken;
    private final int tokensCount;

    public Grammar(int startToken, int tokensCount) {
        this.startToken = startToken;
        this.tokensCount = tokensCount;
    }

    public int getStartToken() {
        return startToken;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("_type", this.getClass().getSimpleName());
        fields.put("startToken", startToken);
        fields.put("tokensCount", tokensCount);
        return fields;
    }
}
