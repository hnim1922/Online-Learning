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
public class ErrorSlider {
   String errorTitle, errorImage, errorBacklink;

    public ErrorSlider() {
    }

    public ErrorSlider(String errorTitle, String errorImage, String errorBacklink) {
        this.errorTitle = errorTitle;
        this.errorImage = errorImage;
        this.errorBacklink = errorBacklink;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(String errorImage) {
        this.errorImage = errorImage;
    }

    public String getErrorBacklink() {
        return errorBacklink;
    }

    public void setErrorBacklink(String errorBacklink) {
        this.errorBacklink = errorBacklink;
    }
   
}
