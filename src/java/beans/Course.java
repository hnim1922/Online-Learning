/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author ihtng
 */
public class Course implements Serializable{

    private int courseID;
    private int subjectID;
    private Subject subject;
    private int registrationID;
    private String title;
    private String tagline;
    private String briefInfo;
    private float status;

    public Course(int courseID, int subjectID, int registrationID, String title, String tagline, String briefInfo, float status) {
        this.courseID = courseID;
        this.subjectID = subjectID;
        this.registrationID = registrationID;
        this.title = title;
        this.tagline = tagline;
        this.briefInfo = briefInfo;
        this.status = status;
    }


    public Course(int courseID, int subjectID, int registrationID, String title, String tagline, String briefInfo, float status, Subject subject) {
        this.courseID = courseID;
        this.subjectID = subjectID;
        this.registrationID = registrationID;
        this.title = title;
        this.tagline = tagline;
        this.briefInfo = briefInfo;
        this.status = status;
        this.subject = subject;
    }

    public Course(int subjectID, int registrationID, String title, String tagline, String briefInfo, float status, Subject subject) {
        this.subjectID = subjectID;
        this.registrationID = registrationID;
        this.title = title;
        this.tagline = tagline;
        this.briefInfo = briefInfo;
        this.status = status;
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Course(int subjectID, int registrationID, String title, String tagline, String briefInfo, float status) {
        this.subjectID = subjectID;
        this.registrationID = registrationID;
        this.title = title;
        this.tagline = tagline;
        this.briefInfo = briefInfo;
        this.status = status;
    }

    public Course() {
    }


    public Course(int courseID, Subject subject, int registrationID, float status) {
        this.courseID = courseID;
        this.subject = subject;
        this.registrationID = registrationID;
        this.status = status;
    }
    
    public float isStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

}