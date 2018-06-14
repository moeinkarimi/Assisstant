package Models;

public class ShowAnswer {

    int ID;
    String Answer, Question;

    public ShowAnswer() {
    }

    public ShowAnswer(String answer,String question) {
        Answer = answer;
        Question = question;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
