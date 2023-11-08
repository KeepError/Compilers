package JSON;

import java.util.Map;
import java.util.stream.Collectors;

public class JSONConverter {
    public static String convert(JSONConvertable object) {
        return toJson(object);
    }

    private static String toJson(JSONConvertable object) {
        Map<String, Object> fields = object.getJSONFields();
        return "{" + fields.keySet().stream().map(field -> String.format("\"%s\": %s", field, getJSONValue(fields.get(field)))).collect(Collectors.joining(", ")) + "}";
    }

    private static String getJSONValue(Object value) {
        try {
            if (value instanceof JSONConvertable) {
                return toJson((JSONConvertable) value);
            } else if (value instanceof String) {
                return "\"" + value + "\"";
            } else {
                return value.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{ERROR}";
        }
    }
}
