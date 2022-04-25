package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber {
    private static final Random RANDOM = new Random();
    private static final int MAX = 9;

    public List<Integer> getNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        while (!(numbers.size() == size)) {
            int i = RANDOM.nextInt(MAX + 1);

            if (i == 0) {
                continue;
            }

            if (!numbers.contains(i)) {
                numbers.add(i);
            }
        }

        return numbers;
    }
}
