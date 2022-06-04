package ui.interactor;

/**
 * 사용자로부터의 입력,
 * 사용자로 내보내는 출력을
 * 담당하는 클래스.
 */
public interface Interactor {
    /**
     * if inputData is empty, must be blocked.
     *
     * @return
     */
    boolean hasRead();

    String read();

    void write(String content);
}
