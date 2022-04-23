package game;

import java.util.*;

public class Game {
    private List<Integer> numbers;
    private static final Random RANDOM = new Random();
    private static final int DIGIT_SIZE = 3;

    public void init() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < DIGIT_SIZE; i++) {
            numbers.add(RANDOM.nextInt(9));
        }

        this.numbers = numbers;
    }

    // for test
    public void init(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public InferResult inferNumbers(List<Integer> questionNumbers) {
        boolean isVictory = false;
        Map<CASE, Integer> matches = new EnumMap<>(CASE.class);
        matches.put(CASE.STRIKE, 0);
        matches.put(CASE.BALL, 0);

        if (questionNumbers.size() != 3) {
            throw new IllegalArgumentException("digit_size is different. questionNumberSize: " + questionNumbers.size());
        }


        for (int i = 0; i < numbers.size(); i++) {
            if (Objects.equals(questionNumbers.get(i), numbers.get(i))) {
                matches.computeIfPresent(CASE.STRIKE, (key, value) -> value + 1);
                continue;
            }

            if (numbers.contains(questionNumbers.get(i))) {
                matches.computeIfPresent(CASE.BALL, (key, value) -> value + 1);
            }
        }

        if (matches.get(CASE.STRIKE) == 3) {
            isVictory = true;
        }

        return new InferResult(matches, isVictory);
    }


    public enum CASE {
        STRIKE,
        BALL
    }
}
