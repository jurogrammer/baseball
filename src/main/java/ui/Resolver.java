package ui;

import game.Game;
import game.InferResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Resolver {
    CLIResolver.Progress resolveStartOrEnd(String startOrEnd);

    List<Integer> resolveNumbers(String numbers);

    String toGameMessage(InferResult inferResult);

    String victoryMessage();

    enum Progress {
        START,
        END
    }
}
