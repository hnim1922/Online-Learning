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
public class ErrorBlog {
     String errorTitle, errorThumbnail, errorDetail,errorDate ;

    public ErrorBlog() {
    }

    public ErrorBlog(String errorTitle, String errorThumbnail, String errorDetail, String errorDate) {
        this.errorTitle = errorTitle;
        this.errorThumbnail = errorThumbnail;
        this.errorDetail = errorDetail;
        this.errorDate = errorDate;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorThumbnail() {
        return errorThumbnail;
    }

    public void setErrorThumbnail(String errorThumbnail) {
        this.errorThumbnail = errorThumbnail;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }
     
}
