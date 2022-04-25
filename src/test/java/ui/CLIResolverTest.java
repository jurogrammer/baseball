package ui;

import game.Game;
import game.InferResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class CLIResolverTest {

    @Test
    @DisplayName("스트링 123을 입력하면, List.of(1,2,3)을 반환한다.")
    void string_to_number_list() {
        //given
        String numbers = "123";
        CLIResolver cliResolver = new CLIResolver();

        //when
        List<Integer> input = cliResolver.resolveNumbers(numbers);

        //then
        Assertions.assertThat(input).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    @DisplayName("print message")
    void output_string_result() {
        Map<Game.CASE, Integer> matches = new EnumMap<>(Game.CASE.class);
        matches.put(Game.CASE.STRIKE, 1);
        matches.put(Game.CASE.BALL, 0);

        InferResult inferResult = new InferResult(matches, false);
        CLIResolver cliResolver = new CLIResolver();

        String message = cliResolver.toGameMessage(inferResult);
        System.out.println("message = " + message);
    }
}