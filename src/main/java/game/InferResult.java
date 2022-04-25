package game;

public class InferResult {

    private int strikeCnt;
    private int ballCnt;

    public InferResult() {
        this.strikeCnt = 0;
        this.ballCnt = 0;
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
}
