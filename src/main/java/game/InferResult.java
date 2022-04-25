package game;

import java.util.Map;

public class InferResult {
    private final Map<Game.CASE, Integer> matches;

    public InferResult(Map<Game.CASE, Integer> matches) {
        this.matches = matches;
    }

    public Map<Game.CASE, Integer> getMatches() {
        return matches;
    }
}
