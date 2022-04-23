package game;

import java.util.Map;

public class InferResult {
    private Map<Game.CASE, Integer> matches;
    private boolean isVictory;

    public InferResult(Map<Game.CASE, Integer> matches, boolean isVictory) {
        this.matches = matches;
        this.isVictory = isVictory;
    }

    public Map<Game.CASE, Integer> getMatches() {
        return matches;
    }

    public boolean isVictory() {
        return isVictory;
    }
}
