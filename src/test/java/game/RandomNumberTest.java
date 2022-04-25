package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberTest {

    @Test
    @DisplayName("랜덤 넘버 생성시 [1,9] 범위의 숫자만 반환해야 한다.")
    void bound_test() {
        //given
        int reps = 1_000_000;
        int size = 3;
        RandomNumber randomNumber = new RandomNumber();


        for (int i = 0; i < reps; i++) {
            //when
            List<Integer> numbers = randomNumber.getNumbers(size);

            // then
            assertThat(numbers).hasSizeBetween(1, 9);
        }
    }

    @Test
    @DisplayName("3개의 자리 수의 수를 생성을 요청하면 3개의 수만 생성해야 한다.")
    void digit_size_test() {
        //given
        int reps = 1_000_000;
        int size = 3;
        RandomNumber randomNumber = new RandomNumber();


        for (int i = 0; i < reps; i++) {
            //when
            List<Integer> numbers = randomNumber.getNumbers(size);

            // then
            assertThat(numbers.size()).isEqualTo(size);
        }
    }
}