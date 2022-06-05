package ui.http;

public class ResponseBuilder {
    private final StringBuilder requestLine;
    private final StringBuilder headers;
    private final StringBuilder body;

    public ResponseBuilder() {
        requestLine = new StringBuilder();
        headers = new StringBuilder();
        body = new StringBuilder();
    }

    public ResponseBuilder ok() {
        requestLine.append("HTTP/2.0 200 OK");
        return this;
    }

    public ResponseBuilder addHeader(String key, String value) {
        headers.append(key).append(": ").append(value);
        addNextLine(headers);
        return this;
    }

    public ResponseBuilder body(String inBody) {
        body.append(inBody);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(requestLine);
        addNextLine(sb);
        sb.append(headers);
        addNextLine(sb);
        sb.append(body);
        addNextLine(sb);
        addNextLine(sb);

        return sb.toString();
    }


    private void addNextLine(StringBuilder sb) {
        sb.append("\r\n");
    }
}
