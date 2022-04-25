package ui.resolvers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TerminalResolverTest {

    @Test
    @DisplayName("스트링 123을 입력하면, List.of(1,2,3)을 반환한다.")
    void string_to_number_list() {
        //given
        String numbers = "123";
        TerminalResolver cliResolver = new TerminalResolver();

        //when
        List<Integer> input = cliResolver.resolveNumbers(numbers);

        //then
        Assertions.assertThat(input).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    @DisplayName("print victory message")
    void output_string_result() {
        TerminalResolver cliResolver = new TerminalResolver();

        String message = cliResolver.victoryMessage();
        System.out.println("message = " + message);
    }
}