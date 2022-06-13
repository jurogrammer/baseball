package ui.http;

public class Response {
    private final String version;
    private final int statusCode;
    private final String statusMessage;
    private final Headers headers;
    private final String body;

    public Response(String version, int statusCode, String statusMessage, Headers headers, String body) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.body = body;
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(version).append(" ");
        sb.append(statusCode).append(" ");
        sb.append(statusMessage).append("\r\n");
        sb.append(headers).append("\r\n");
        sb.append(body).append("\r\n").append("\r\n");

        return sb.toString();
    }

    private void addNextLine(StringBuilder sb) {
        sb.append("\r\n");
    }

    public static class ResponseBuilder {
        private String version;
        private int statusCode;
        private String statusMessage;
        private final Headers headers;
        private String body;


        public ResponseBuilder() {
            headers = new Headers();
        }

        public ResponseBuilder ok() {
            this.version = "HTTP/1.1";
            this.statusCode = 200;
            this.statusMessage = "OK";
            return this;
        }

        public ResponseBuilder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public ResponseBuilder body(String inBody) {
            this.body = inBody;
            return this;
        }

        public Response build() {
            return new Response(version, statusCode, statusMessage, headers, body);
        }
    }

}
