import game.Game;
import game.dto.InferResult;
import game.exception.GameException;
import ui.Interactor;
import ui.Progress;
import ui.clientserver.ClientServerResolver;
import ui.clientserver.ClientServerUIFactory;
import ui.clientserver.http.Action;
import ui.clientserver.http.HttpUIFactory;
import ui.exception.UIException;

import java.util.List;

public class ClientServerApp implements App {

    @Override
    public void run() {
        ClientServerUIFactory uiFactory = new HttpUIFactory();
        Interactor interactor = uiFactory.createInteractor();
        ClientServerResolver resolver = uiFactory.createResolver();
        Game game = new Game();

        while (interactor.hasRead()) {
            try {
                String read = interactor.read();
                Action action = resolver.resolveAction(read);

                switch (action) {
                    case GAME_PAGE:
                        game.init();
                        interactor.write(resolver.startMessage());
                        break;
                    case INFER:
                        List<Integer> integers = resolver.resolveNumbers(read);
                        InferResult inferResult = game.inferNumbers(integers);
                        interactor.write(resolver.toGameMessage(inferResult));
                        break;
                    case RESTART:
                        Progress progress = resolver.resolveStartOrEnd(read);
                        if (progress == Progress.START) {
                            game.init();
                            interactor.write(resolver.toGameMessage("게임이 재시작되었습니다."));
                            break;
                        }

                        interactor.write(resolver.toGameMessage("게임이 종료되었습니다."));
                        break;
                }
            } catch (GameException | UIException retryableException) {
                retryableException.printStackTrace();
                interactor.write(resolver.toGameMessage(retryableException.getMessage()));
            } catch (Exception e) {
                e.printStackTrace();
                interactor.write(resolver.toGameMessage("치명적인 오류가 발생했습니다."));
            }
        }
    }
}
