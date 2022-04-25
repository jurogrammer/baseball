package game;

import game.dto.InferResult;
import game.exceptions.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game {
    private List<Integer> numbers;
    private static final Random RANDOM = new Random();
    private static final int DIGIT_SIZE = 3;
    private boolean isVictory;

    public void init() {
        numbers = new ArrayList<>();
        while (!(numbers.size() == DIGIT_SIZE)) {
            int i = RANDOM.nextInt(10);

            if (i == 0) {
                continue;
            }

            if (!numbers.contains(i)) {
                numbers.add(i);
            }
        }
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

        if (questionNumbers.size() != 3) {
            throw new GameException("digit_size is different. questionNumberSize: " + questionNumbers.size());
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
        }

        return inferResult;
    }
}
