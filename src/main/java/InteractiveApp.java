import game.Game;
import game.dto.InferResult;
import game.exception.GameException;
import ui.Progress;
import ui.Interactor;
import ui.reactive.ReactiveResolver;
import ui.reactive.ReactiveUIFactory;
import ui.exception.UIException;
import ui.reactive.terminal.TerminalFactory;

import java.util.List;

/**
 * DI 및 flow control 하는 클래스
 */
public class InteractiveApp implements App {
    public void run() {
        // di
        Game game = new Game();
        ReactiveUIFactory uiFactory = new TerminalFactory();
        ReactiveResolver resolver = uiFactory.createResolver();
        Interactor interactor = uiFactory.createInteractor();

        game.init();
        interactor.write(resolver.startMessage());

        while (interactor.hasRead()) {
            startGame(game, resolver, interactor);
            interactor.write(resolver.victoryMessage());

            Progress progress = resolver.resolveStartOrEnd(interactor.read());
            if (progress == Progress.END) {
                break;
            }

            game.init();
        }
    }

    private void startGame(Game game, ReactiveResolver resolver, Interactor interactor) {
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
