package ui.http;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public RequestParser() {
    }

    public Request parse(String message) {
        String[] split = message.split("\r\n\r\n");
        String nonBody = split[0];
        String body = split[1];
        String[] requestLineAndHeaders = nonBody.split("\r\n");

        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < requestLineAndHeaders.length; i++) {
            String[] keyValue = requestLineAndHeaders[i].split(": ");
            headers.put(keyValue[0], keyValue[1]);
        }

        String requestLine = requestLineAndHeaders[0];
        String[] splitRequestLine = requestLine.split(" ");

        String method = splitRequestLine[0];
        String url = splitRequestLine[1];
        String phrase = splitRequestLine[2];

        return new Request(Method.valueOf(method.toUpperCase()), url, phrase, headers, body);
    }
}
