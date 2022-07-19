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
public class ErrorSubject {    
    private String thumbnailimageError;
    private String nameError;
    private String informationError;

    public ErrorSubject(String thumbnailimageError, String nameError, String informationError) {
        this.thumbnailimageError = thumbnailimageError;
        this.nameError = nameError;
        this.informationError = informationError;
    }

    public ErrorSubject() {
    }

    public String getThumbnailimageError() {
        return thumbnailimageError;
    }

    public void setThumbnailimageError(String thumbnailimageError) {
        this.thumbnailimageError = thumbnailimageError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getInformationError() {
        return informationError;
    }

    public void setInformationError(String informationError) {
        this.informationError = informationError;
    }    
}
