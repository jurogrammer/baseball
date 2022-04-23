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
    public void initWithNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Map<CASE, Integer> inferNumbers(List<Integer> questionNumbers) {
        Map<CASE, Integer> matches = new EnumMap<>(CASE.class);
        if (questionNumbers.size() != 3) {
            throw new IllegalArgumentException("digit_size is different. questionNumberSize: " + questionNumbers.size());
        }

        return null;
    }


    enum CASE {
        STRIKE,
        BALL
    }
}
