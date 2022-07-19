/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author acer
 */
public class Quiz {
    private int quizID, lessonID;
    private String quizName;
    private String level;
    private int numOfQuestion;
    private int duration;
    private float passRate;
    private String quizType;
    private String subjectName;
    private boolean isTaken;

    public Quiz(int quizID, int lessonID, String quizName, String level, int numOfQuestion, int duration, float passRate, String quizType, String subjectName, boolean isTaken) {
        this.quizID = quizID;
        this.lessonID = lessonID;
        this.quizName = quizName;
        this.level = level;
        this.numOfQuestion = numOfQuestion;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = quizType;
        this.subjectName = subjectName;
        this.isTaken = isTaken;
    }

    public Quiz() {
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPassRate() {
        return passRate;
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public Quiz(int quizID, int lessonID, String quizName, String level, int numOfQuestion, int duration, float passRate, String quizType) {
        this.quizID = quizID;
        this.lessonID = lessonID;
        this.quizName = quizName;
        this.level = level;
        this.numOfQuestion = numOfQuestion;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = quizType;
    }

    public Quiz(int quizID, int lessonID) {
        this.quizID = quizID;
        this.lessonID = lessonID;
    }



    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isIsTaken() {
        return isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Quiz(int quizID, String quizName) {
        this.quizID = quizID;
        this.quizName = quizName;
    }
}
