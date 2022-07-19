/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Admin
 */
public class Calendar {
    private int countmonth, countdate, month, date;

    public Calendar() {
    }

    public Calendar(int countmonth, int countdate, int month, int date) {
        this.countmonth = countmonth;
        this.countdate = countdate;
        this.month = month;
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCountmonth() {
        return countmonth;
    }

    public void setCountmonth(int countmonth) {
        this.countmonth = countmonth;
    }

    public int getCountdate() {
        return countdate;
    }

    public void setCountdate(int countdate) {
        this.countdate = countdate;
    }
}
