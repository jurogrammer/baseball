package ui.resolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.exception.UIException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(input).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    @DisplayName("print victory message")
    void output_string_result() {
        TerminalResolver cliResolver = new TerminalResolver();

        String message = cliResolver.victoryMessage();
        System.out.println("message = " + message);
    }

    @Test
    @DisplayName("숫자 이외의 값을 전달할 경우 예외를 발생한다.")
    void must_be_number() {
        //given
        TerminalResolver cliResolver = new TerminalResolver();

        //when & then
        UIException uiException = assertThrows(UIException.class, () -> cliResolver.resolveNumbers("a23"));

        assertThat(uiException.getMessage()).contains("입력 값은 숫자여야 합니다");
    }
}