import game.Game;
import game.dto.InferResult;
import game.exceptions.GameException;
import ui.exceptions.UIException;
import ui.interactors.Interactor;
import ui.interactors.TerminalInteractor;
import ui.resolvers.Resolver;
import ui.resolvers.TerminalResolver;

import java.util.List;

/**
 * DI 및 flow control 하는 클래스
 */
public class GameApp {
    public static void run() {
        // di
        Game game = new Game();
        Resolver resolver = new TerminalResolver();
        Interactor interactor = new TerminalInteractor();

        game.init();
        interactor.write(resolver.startMessage());

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
            try {
                List<Integer> question = resolver.resolveNumbers(interactor.read());
                InferResult inferResult = game.inferNumbers(question);
                String message = resolver.toGameMessage(inferResult);
                interactor.write(message);
            } catch (GameException | UIException ex) {
                interactor.write(resolver.toGameMessage(ex.getMessage()));
            }
        }
    }
}
