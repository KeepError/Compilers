package JSON;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JSONSerializer {
    public static String serialize(Object object) {
        return toJson(object);
    }

    private static String toJson(Object object) {
        try {
            switch (object) {
                case JSONSerializable jsonSerializable -> {
                    Map<String, Object> fields = jsonSerializable.getJSONFields();
                    return "{" + fields.keySet().stream().map(field -> String.format("\"%s\": %s", field, toJson(fields.get(field)))).collect(Collectors.joining(", ")) + "}";
                }
                case String s -> {
                    return "\"" + object + "\"";
                }
                case Character c -> {
                    return "'" + object + "'";
                }
                case Iterable iterable -> {
                    StringJoiner joiner = new StringJoiner(", ");
                    for (Object element : iterable) {
                        joiner.add(toJson(element));
                    }
                    return "[" + joiner + "]";
                }
                case Map map -> {
                    StringJoiner joiner = new StringJoiner(", ");
                    for (Object key : map.keySet()) {
                        joiner.add(String.format("%s: %s", toJson(key), toJson(((Map<?, ?>) object).get(key))));
                    }
                    return "{" + joiner + "}";
                }
                case null -> {
                    return "null";
                }
                default -> {
                    return object.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
