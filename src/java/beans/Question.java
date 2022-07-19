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
public class Question  implements Comparable<Question>{

    private int questionID, quizID;
    private String content;
    private String level;
    private String lessonName;
    private String subjectname;
    private String dimensionName;

    public Question(int questionID, int quizID, String content, String level, String lessonName, String subjectname, String dimensionName) {
        this.questionID = questionID;
        this.quizID = quizID;
        this.content = content;
        this.level = level;
        this.lessonName = lessonName;
        this.subjectname = subjectname;
        this.dimensionName = dimensionName;
    }
    
    public Question(int questionID) {
        this.questionID = questionID;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public Question() {
    }

    public Question(int questionID, int quizID, String content) {
        this.questionID = questionID;
        this.quizID = quizID;
        this.content = content;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Question o) {
        if(questionID<o.questionID)
        {
            return -1;
        }
        if(questionID==o.questionID)
        {
            return 0;
        }
        return 1;
    }
    
}
