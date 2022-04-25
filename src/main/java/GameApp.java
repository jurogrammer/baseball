import game.Game;
import game.InferResult;
import ui.CLIInteractor;
import ui.CLIResolver;
import ui.Interactor;
import ui.Resolver;

import java.util.List;

public class GameApp {
    public static void run() {
        Game game = new Game();
        Resolver resolver = new CLIResolver();
        Interactor interactor = new CLIInteractor();
        game.init();

        while (interactor.hasRead()) {
            startGame(game, resolver, interactor);
            interactor.write(resolver.victoryMessage());

            Resolver.Progress progress = resolver.resolveStartOrEnd(interactor.read());
            if (progress == Resolver.Progress.END) {
                break;
            }

            game.init();
        }
    }

    private static void startGame(Game game, Resolver resolver, Interactor interactor) {
        while (!game.isVictory()) {
            List<Integer> question = resolver.resolveNumbers(interactor.read());
            InferResult inferResult = game.inferNumbers(question);
            String message = resolver.toGameMessage(inferResult);
            interactor.write(message);
        }
    }
}