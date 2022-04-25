package ui.interactors;

import java.util.Scanner;

public class CLIInteractor implements Interactor {
    private final Scanner scanner;

    public CLIInteractor() {
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
