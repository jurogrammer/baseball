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
        try {
            UIFactory uiFactory = new HttpFactory();
            Interactor interactor = uiFactory.createInteractor();
            Resolver resolver = uiFactory.createResolver();
            Game game = new Game();

            if (interactor.hasRead()) {
                // client-server 구조에서는 반드시 요청을 보내야만 start message를 보낼 수 있다.
                String read = interactor.read();
                // start message는 게임을 할 수 있는 index page여야 한다.
                interactor.write(resolver.startMessage());
                game.init();
            }

            while (interactor.hasRead()) {
                startGame(game, resolver, interactor);
                interactor.write(resolver.victoryMessage());

                Resolver.Progress progress = resolver.resolveStartOrEnd(interactor.read());
                if (progress == Resolver.Progress.END) {
                    break;
                }

                game.init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void startGame(Game game, Resolver resolver, Interactor interactor) {
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
