package ui.http;

public class InferResponse {
    private String message;
    private boolean isVictory;

    public InferResponse() {
    }

    public InferResponse(String message, boolean isVictory) {
        this.message = message;
        this.isVictory = isVictory;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsVictory() {
        return isVictory;

    }

}
