import game.Game;
import game.InferResult;
import ui.CLIResolver;

import java.util.List;
import java.util.Scanner;

public class GameApp {
    private static final String RESTART = "1";
    private static final String END = "2";

    public static void run() {
        Game game = new Game();
        CLIResolver cliResolver = new CLIResolver();
        game.init();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();

            if (next.equals(RESTART)) {
                game.init();
            } else if (next.equals(END)) {
                break;
            } else {
                List<Integer> question = cliResolver.input(next);
                InferResult inferResult = game.inferNumbers(question);
                String message = cliResolver.gameInputMessage(inferResult);
                System.out.println(message);
            }
        }
    }
}
