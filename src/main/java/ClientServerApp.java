import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.dto.InferResult;
import game.exception.GameException;
import ui.Resolver;
import ui.exception.UIException;
import ui.http.Action;
import ui.http.HttpInteractor;
import ui.http.HttpResolver;
import ui.http.RequestParser;

import java.util.List;

public class ClientServerApp implements App {

    @Override
    public void run() {
        HttpInteractor interactor = new HttpInteractor();
        HttpResolver resolver = new HttpResolver(new ObjectMapper(), new RequestParser());
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
                        // game쪽에 이 로직을 넣는게 더 맞는 듯.
                        if (game.isVictory()) {
                            throw new GameException("게임은 종료되었습니다.");
                        }
                        List<Integer> integers = resolver.resolveNumbers(read);
                        InferResult inferResult = game.inferNumbers(integers);
                        interactor.write(resolver.toGameMessage(inferResult));
                        break;
                    case RESTART:
                        Resolver.Progress progress = resolver.resolveStartOrEnd(read);
                        if (progress == Resolver.Progress.START) {
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
