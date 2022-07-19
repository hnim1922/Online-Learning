/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author acer
 */
public class Slider implements Serializable {
    int sliderID;
    String title,backlink;
    boolean status;
    String image;

    public Slider() {
    }

    public Slider(int sliderID, String title, String image,String backlink, boolean status ) {
        this.sliderID = sliderID;
        this.title = title;
        this.backlink = backlink;
        this.status = status;
        this.image = image;
    }

    public int getSliderID() {
        return sliderID;
    }

    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
    
    
}
