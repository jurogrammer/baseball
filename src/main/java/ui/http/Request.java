package ui.http;

import java.util.Map;

public class Request {
    private String method;
    private String url;
    private String phase;
    private Map<String, String> header;
    private String body;

    public Request(String method, String url, String phase, Map<String, String> header, String body) {
        this.method = method;
        this.url = url;
        this.phase = phase;
        this.header = header;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getPhase() {
        return phase;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }
}
