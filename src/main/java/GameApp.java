import game.Game;
import game.InferResult;
import ui.CLIInteractor;
import ui.CLIResolver;
import ui.Interactor;

import java.util.List;

public class GameApp {
    private static final String RESTART = "1";
    private static final String END = "2";

    public static void run() {
        Game game = new Game();
        CLIResolver cliResolver = new CLIResolver();
        Interactor interactor = new CLIInteractor();
        game.init();

        while (interactor.hasRead()) {
            String read = interactor.read();
            CLIResolver.Progress progress = cliResolver.resolveStartOrEnd(read);
            if (progress == CLIResolver.Progress.START) {
                game.init();
            } else if (progress == CLIResolver.Progress.END) {
                break;
            } else {
                List<Integer> question = cliResolver.resolveNumbers(read);
                InferResult inferResult = game.inferNumbers(question);

                String message = cliResolver.toGameMessage(inferResult);
                interactor.write(message);
            }
        }

    }
}
