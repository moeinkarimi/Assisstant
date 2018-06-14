package Models;

public class Scores {
    private int SID, SGameID, Score, SQuestionID;

    public Scores() {
    }

    public Scores(int sGameID,int score,int squestionID) {
        SGameID = sGameID;
        Score = score;
        SQuestionID = squestionID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getSGameID() {
        return SGameID;
    }

    public void setSGameID(int SGameID) {
        this.SGameID = SGameID;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getSQuestionID() {
        return SQuestionID;
    }

    public void setSQuestionID(int squestionID) {
        SQuestionID = squestionID;
    }
}
