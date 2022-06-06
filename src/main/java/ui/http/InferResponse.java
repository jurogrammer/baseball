package ui.http;

public class InferResponse {
    private String message;

    public InferResponse() {
    }

    public InferResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
