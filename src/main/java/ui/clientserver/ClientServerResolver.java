package ui.clientserver;

import game.dto.InferResult;
import ui.Progress;
import ui.clientserver.http.Action;

import java.util.List;

public interface ClientServerResolver {
    Action resolveAction(String httpRequest);

    Progress resolveStartOrEnd(String startOrEnd);

    List<Integer> resolveNumbers(String httpNumbers);

    String toGameMessage(InferResult inferResult);

    String toGameMessage(String message);

    String startMessage();
}
