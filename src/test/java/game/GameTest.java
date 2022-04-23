package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void not_match_numbers() {
        Game game = new Game();
        game.init();
        List<Integer> questionNumbers = List.of(1, 2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> game.inferNumbers(questionNumbers));
    }

    @Test
    @DisplayName("정답이 123일 때, 145은 1S, 승리하지 않음")
    void one_strike() {
        Game game = new Game();
        game.init(List.of(1, 2, 3));

        InferResult inferResult = game.inferNumbers(List.of(1, 4, 5));
        Map<Game.CASE, Integer> matches = inferResult.getMatches();
        boolean victory = inferResult.isVictory();

        assertThat(victory).isFalse();
        assertThat(matches.get(Game.CASE.STRIKE)).isEqualTo(1);
        assertThat(matches.get(Game.CASE.BALL)).isEqualTo(0);
    }

    @Test
    @DisplayName("정답이 123일 때, 134은 1S 1B , 승리하지 않음")
    void one_ball() {
        Game game = new Game();
        game.init(List.of(1, 2, 3));

        InferResult inferResult = game.inferNumbers(List.of(1, 3, 4));
        Map<Game.CASE, Integer> matches = inferResult.getMatches();
        boolean victory = inferResult.isVictory();

        assertThat(victory).isFalse();
        assertThat(matches.get(Game.CASE.STRIKE)).isEqualTo(1);
        assertThat(matches.get(Game.CASE.BALL)).isEqualTo(1);
    }

    @Test
    @DisplayName("정답이 123일 때, 456은 0S 0B, 승리하지 않음")
    void nothing() {
        Game game = new Game();
        game.init(List.of(1, 2, 3));

        InferResult inferResult = game.inferNumbers(List.of(4, 5, 6));
        Map<Game.CASE, Integer> matches = inferResult.getMatches();
        boolean victory = inferResult.isVictory();

        assertThat(victory).isFalse();
        assertThat(matches.get(Game.CASE.STRIKE)).isEqualTo(0);
        assertThat(matches.get(Game.CASE.BALL)).isEqualTo(0);
    }

    @Test
    @DisplayName("정답이 123일 때, 123은 3S 0B, 승리")
    void victory() {
        Game game = new Game();
        game.init(List.of(1, 2, 3));

        InferResult inferResult = game.inferNumbers(List.of(1, 2, 3));
        Map<Game.CASE, Integer> matches = inferResult.getMatches();
        boolean victory = inferResult.isVictory();

        assertThat(victory).isTrue();
        assertThat(matches.get(Game.CASE.STRIKE)).isEqualTo(3);
        assertThat(matches.get(Game.CASE.BALL)).isEqualTo(0);
    }
}