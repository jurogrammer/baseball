import game.Game;
import game.dto.InferResult;
import ui.interactors.TerminalInteractor;
import ui.resolvers.TerminalResolver;
import ui.interactors.Interactor;
import ui.resolvers.Resolver;

import java.util.List;

/**
 * DI 및 flow control 하는 클래스
 */
public class GameApp {
    public static void run() {
        Game game = new Game();
        Resolver resolver = new TerminalResolver();
        Interactor interactor = new TerminalInteractor();
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
