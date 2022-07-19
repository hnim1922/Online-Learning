/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import utils.BlogStatus;

/**
 *
 * @author Minh
 */

public class Blog implements Serializable{
    private int blogID;
    private int userID;
    private String fullname;
    private int categoryID;
    private String thumbnail;
    private String title;
    private String updatedDate;
    private String blogDetail;
    private boolean flag;
    private BlogStatus status;

    public Blog(int blogID, int userID, int categoryID, String thumbnail, String title, String updatedDate, String blogDetail, boolean flag, BlogStatus status) {
        this.blogID = blogID;
        this.userID = userID;
        this.categoryID = categoryID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.updatedDate = updatedDate;
        this.blogDetail = blogDetail;
        this.flag = flag;
        this.status = status;
    }
    
    public Blog(int blogID, String fullname, int categoryID, String thumbnail, String title, String updatedDate, String blogDetail, boolean flag, BlogStatus status) {
        this.blogID = blogID;
        this.fullname = fullname;
        this.categoryID = categoryID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.updatedDate = updatedDate;
        this.blogDetail = blogDetail;
        this.flag = flag;
        this.status = status;
    }

    public Blog() {
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBlogDetail() {
        return blogDetail;
    }

    public void setBlogDetail(String blogDetail) {
        this.blogDetail = blogDetail;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BlogStatus isStatus() {
        return status;
    }

    public void setStatus(BlogStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogID=" + blogID + ", userID=" + userID + ", categoryID=" + categoryID + ", thumbnail=" + thumbnail + ", title=" + title + ", updatedDate=" + updatedDate + ", blogDetail=" + blogDetail + ", flag=" + flag + ", status=" + status + '}';
    }
    
    
    
}
