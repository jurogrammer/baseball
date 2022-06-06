package ui.http;

import java.util.Map;

public class Request {
    private Method method;
    private String url;
    private String phase;
    private Map<String, String> header;
    private String body;

    public Request(Method method, String url, String phase, Map<String, String> header, String body) {
        this.method = method;
        this.url = url;
        this.phase = phase;
        this.header = header;
        this.body = body;
    }

    public Method getMethod() {
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
