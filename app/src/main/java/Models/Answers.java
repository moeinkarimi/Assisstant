package Models;

public class Answers {

    private int AID;
    private String Answer;
    private int AGameID;
    private int QuestionID;

    public Answers() {
    }

    public Answers(String answer,int AGameID,int questionID) {
        Answer = answer;
        this.AGameID = AGameID;
        QuestionID = questionID;
    }

    public Answers(int AID,String answer,int AGameID,int questionID) {
        this.AID = AID;
        Answer = answer;
        this.AGameID = AGameID;
        QuestionID = questionID;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getAGameID() {
        return AGameID;
    }

    public void setAGameID(int AGameID) {
        this.AGameID = AGameID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }
}
