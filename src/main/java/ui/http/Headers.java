package ui.http;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    Map<String, String> headers;

    public Headers() {
        this.headers = new HashMap<>();
    }

    public void put(String key, String value) {
        headers.put(key, value);
    }

    public String get(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> keyVal : headers.entrySet()) {
            sb.append(keyVal.getKey()).append(": ").append(keyVal.getValue()).append("\r\n");
        }
        return sb.toString();
    }
}
