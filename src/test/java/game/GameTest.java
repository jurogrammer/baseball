package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameTest {

    @Test
    void not_match_numbers() {
        Game game = new Game();
        game.init();
        List<Integer> questionNumbers = List.of(1,2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> game.inferNumbers(questionNumbers));
    }

    @Test
    void one_strike() {

    }

    @Test
    void one_ball() {

    }

    @Test
    void nothing() {

    }
}