

package beans;

public class Answer {

    private int answerID;
    private int questionID;
    private int result;
    private boolean resultBoolean;
    private String content;

    public Answer(int answerID, int questionID, int result, String content) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.result = result;
        this.content = content;
    }
  
    public Answer(int answerID, int questionID, boolean resultBoolean, String content){
        this.answerID = answerID;
        this.questionID = questionID;
        this.resultBoolean = resultBoolean;
        this.content = content;
    }

    public Answer(int questionID, int result, String content) {
        this.questionID = questionID;
        this.result = result;
        this.content = content;
    }
  
    public Answer(int questionID, boolean resultBoolean, String content) {
        this.questionID = questionID;
        this.resultBoolean = resultBoolean;
        this.content = content;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }


    public boolean isResultBoolean() {
        return resultBoolean;
    }

    public void setResultBoolean(boolean resultBolean) {
        this.resultBoolean = resultBolean;
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
