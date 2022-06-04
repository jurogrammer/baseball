package ui.terminal;

import game.dto.InferResult;
import ui.exception.UIException;
import ui.Resolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Terminal <-> GAME간 input, output을 변환시켜주는 클래스.
 * 1. Terminal에서 들어온 String을 GAME INPUT에 알맞은 값으로 변환시키고
 * 2. GAME의 OUTPUT을 CLI에 반환될 값으로 반환시켜준다.
 */
public class TerminalResolver implements Resolver {

    @Override
    public Progress resolveStartOrEnd(String startOrEnd) {
        if ("1".equals(startOrEnd)) {
            return Progress.START;
        } else if ("2".equals(startOrEnd)) {
            return Progress.END;
        } else {
            throw new UIException("입력 값은 1 또는 2여야만 합니다. 입력: " + startOrEnd);
        }
    }

    @Override
    public List<Integer> resolveNumbers(String numbers) {
        try {
            return Arrays.stream(numbers.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            throw new UIException("입력 값은 숫자여야 합니다. 입력: " + numbers);
        }

    }

    @Override
    public String toGameMessage(InferResult inferResult) {
        if (inferResult.getStrikeCnt() == 0 && inferResult.getBallCnt() == 0) {
            return "낫싱";
        }
        if (inferResult.getBallCnt() == 0) {
            return String.format("%d 스트라이크", inferResult.getStrikeCnt());
        }

        if (inferResult.getStrikeCnt() == 0) {
            return String.format("%d 볼", inferResult.getBallCnt());
        }

        return String.format("%d 스트라이크 %d 볼 ", inferResult.getStrikeCnt(), inferResult.getBallCnt());
    }

    @Override
    public String toGameMessage(String message) {
        return message;
    }

    @Override
    public String startMessage() {
        return "게임 시작!!! 3자리의 숫자를 입력하세요.";
    }

    @Override
    public String victoryMessage() {
        return "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    }
}
