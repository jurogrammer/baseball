package ui.resolvers;

import game.dto.InferResult;

import java.util.List;

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
