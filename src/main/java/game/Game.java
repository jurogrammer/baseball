package game;

import game.dto.InferResult;
import game.exception.GameException;

import java.util.List;
import java.util.Objects;

public class Game {
    private static final int DIGIT_SIZE = 3;
    private final RandomNumber randomNumber;
    private List<Integer> numbers;
    private boolean isVictory;

    public Game() {
        this.randomNumber = new RandomNumber();
    }

    public void init() {
        numbers = randomNumber.getNumbers(DIGIT_SIZE);
        this.isVictory = false;
    }

    public boolean isVictory() {
        return this.isVictory;
    }

    // for test
    public void init(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public InferResult inferNumbers(List<Integer> questionNumbers) {
        InferResult inferResult = new InferResult();

        if (questionNumbers.size() != DIGIT_SIZE) {
            throw new GameException(DIGIT_SIZE + "자리 숫자를 입력해주세요. 현재 입력한 숫자의 자리 수: " + questionNumbers.size());
        }

        for (int i = 0; i < numbers.size(); i++) {
            if (Objects.equals(questionNumbers.get(i), numbers.get(i))) {
                inferResult.addStrike();
                continue;
            }

            if (numbers.contains(questionNumbers.get(i))) {
                inferResult.addBall();
            }
        }

        if (inferResult.getStrikeCnt() == 3) {
            isVictory = true;
            inferResult.toVictory();
        }

        return inferResult;
    }
}
