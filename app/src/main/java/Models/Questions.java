package Models;

public class Questions {
    private int QID;
    private String Question;
    private int QGameID;
    private int QuestionID;
    private boolean QFlag;

    public Questions() {
    }

    public Questions(boolean QFlag) {
        this.QFlag = QFlag;
    }

    public Questions(String question) {
        Question = question;
    }

    public Questions(String question,int QGameID,int questionID,boolean qFlag) {
        Question = question;
        this.QGameID = QGameID;
        QuestionID = questionID;
    }

    public Questions(int QID,String question,int QGameID,int questionID,boolean qFlag) {
        this.QID = QID;
        Question = question;
        this.QGameID = QGameID;
        QuestionID = questionID;
    }

    public int getQID() {
        return QID;
    }

    public void setQID(int QID) {
        this.QID = QID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public int getQGameID() {
        return QGameID;
    }

    public void setQGameID(int QGameID) {
        this.QGameID = QGameID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public boolean isQFlag() {
        return QFlag;
    }

    public void setQFlag(boolean QFlag) {
        this.QFlag = QFlag;
    }
}
