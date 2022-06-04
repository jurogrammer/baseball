package ui.interactor;

import java.util.Scanner;

public class TerminalInteractor implements Interactor {
    private final Scanner scanner;

    public TerminalInteractor() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean hasRead() {
        return scanner.hasNext();
    }

    @Override
    public String read() {
        return scanner.next();
    }

    @Override
    public void write(String content) {
        System.out.println(content);
    }
}
