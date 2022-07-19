/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minh
 */
public class Registration implements Serializable{
    private int regisID;
    private Date regisTime;
    private int status;
    private Date validFrom;
    private Date validTo;
    private double totalPrice;
    private int userID;
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    

    public int getRegisID() {
        return regisID;
    }

    public void setRegisID(int regisID) {
        this.regisID = regisID;
    }

    public Date getRegisTime() {
        return regisTime;
    }

    public Registration(Date regisTime, Date validFrom, Date validTo, int userID) {
        this.regisTime = regisTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.userID = userID;
    }

    public Registration(int regisID, Date regisTime, int status, Date validFrom, Date validTo, int userID, String userEmail) {
        this.regisID = regisID;
        this.regisTime = regisTime;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.userID = userID;
        this.userEmail = userEmail;
    }
    
    public Registration(int regisID, Date regisTime, int status, Date validFrom, Date validTo, int userID ){
        this.regisID = regisID;
        this.regisTime = regisTime;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.userID = userID;
    }

    public void setRegisTime(Date regisTime) {
        this.regisTime = regisTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "registration: registrationID: " + regisID + ", registrationTime: " + regisTime.toString() + ", status: " + status; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
