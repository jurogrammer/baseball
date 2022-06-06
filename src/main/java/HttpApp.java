import game.Game;
import game.dto.InferResult;
import game.exception.GameException;
import ui.Interactor;
import ui.Resolver;
import ui.UIFactory;
import ui.exception.UIException;
import ui.http.HttpFactory;

import java.util.List;

public class HttpApp implements App {

    @Override
    public void run() {
        UIFactory uiFactory = new HttpFactory();
        Interactor interactor = uiFactory.createInteractor();
        Resolver resolver = uiFactory.createResolver();
        Game game = new Game();

        if (interactor.hasRead()) {
            String read = interactor.read();
            interactor.write(resolver.startMessage());
            game.init();
        }

        while (interactor.hasRead()) {
            try {
                String read = interactor.read();
                if (resolver.isIllegal(read)) {
                    interactor.write("");
                    continue;
                }
                List<Integer> question = resolver.resolveNumbers(read);
                InferResult inferResult = game.inferNumbers(question);
                String message = resolver.toGameMessage(inferResult);
                interactor.write(message);

                if (game.isVictory()) {
                    interactor.write(resolver.victoryMessage());
                    Resolver.Progress progress = resolver.resolveStartOrEnd(interactor.read());
                    if (progress == Resolver.Progress.END) {
                        break;
                    }
                    game.init();
                }
            } catch (GameException | UIException ex) {
                ex.printStackTrace();
                interactor.write(resolver.toGameMessage(ex.getMessage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
