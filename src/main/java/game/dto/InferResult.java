package game.dto;

public class InferResult {

    private int strikeCnt;
    private int ballCnt;
    private boolean isVictory;

    public InferResult() {
        this.strikeCnt = 0;
        this.ballCnt = 0;
        this.isVictory = false;
    }

    public void addStrike() {
        this.strikeCnt++;
    }

    public void addBall() {
        this.ballCnt++;
    }

    public int getStrikeCnt() {
        return strikeCnt;
    }

    public int getBallCnt() {
        return ballCnt;
    }

    public boolean getVictory() {
        return isVictory;
    }

    public void toVictory() {
        this.isVictory = true;
    }
}
