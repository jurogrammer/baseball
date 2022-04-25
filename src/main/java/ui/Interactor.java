package ui;

public interface Interactor {
    /**
     * if inputData is empty, must be blocked.
     * @return
     */
    boolean hasRead();
    String read();
    void write(String content);
}
