package ui.reactive;

import game.dto.InferResult;
import ui.Progress;

import java.util.List;

/**
 * 사용자의 관점의 입력 -> 게임 관점의 입력으로,
 * 게임 관점의 결과 -> 사용자 관점의 결과로 출력하는 클래스.
 */
public interface ReactiveResolver {
    Progress resolveStartOrEnd(String startOrEnd);

    List<Integer> resolveNumbers(String numbers);

    String toGameMessage(InferResult inferResult);

    String toGameMessage(String message);

    String startMessage();

    String victoryMessage();
}
