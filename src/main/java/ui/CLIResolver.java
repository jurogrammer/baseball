package ui;

import game.Game;
import game.InferResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CLI <-> GAME간 input, output을 변환시켜주는 클래스.
 * 1. CLI에서 들어온 String을 GAME INPUT에 알맞은 값으로 변환시키고
 * 2. GAME의 OUTPUT을 CLI에 반환될 값으로 반환시켜준다.
 */
public class CLIResolver implements Resolver {

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
        return Arrays.stream(numbers.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public String toGameMessage(InferResult inferResult) {
        Map<Game.CASE, Integer> matches = inferResult.getMatches();

        int strikes = matches.get(Game.CASE.STRIKE);
        int balls = matches.get(Game.CASE.BALL);
        if (strikes == 0 && balls == 0) {
            return "낫싱";
        }
        if (balls == 0) {
            return String.format("%d 스트라이크", strikes);
        }

        if (strikes == 0) {
            return String.format("%d 볼", balls);
        }

        return String.format("%d 스트라이크 %d 볼 ", strikes, balls);
    }

    @Override
    public String victoryMessage() {
        return "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    }
}
