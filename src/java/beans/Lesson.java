/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


public class Lesson {
    private int subjectID;
    private int lessonID;
    private String content;
    private String lessonName;
    private boolean status;
    private Subject subject;
    private int courseID;

    public Lesson() {
    }

    public Lesson(int lessonID, int subjectID, String content) {
        this.lessonID = lessonID;
        this.subjectID = subjectID;
        this.content = content;
    }

    public Lesson(int lessonID, String content, String lessonName, Subject subject, int courseID) {
        this.lessonID = lessonID;
        this.content = content;
        this.lessonName = lessonName;
        this.subject = subject;
        this.courseID = courseID;
    }


    public Lesson(int lessonID, String content, String lessonName, boolean status) {
        this.lessonID = lessonID;
        this.content = content;
        this.lessonName = lessonName;
        this.status = status;
    }

    public Lesson(int lessonID, String content, String lessonName) {
        this.lessonID = lessonID;
        this.content = content;
        this.lessonName = lessonName;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
