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
public class Subject implements Serializable{

    private int subjectID;
//    private int pricePackageID;
    private PricePackage pricePackage;
    private int dimensionID;
    private String categoryName;
    private String thumbnail;
    private String name;
    private boolean featureFlag;
    private boolean status;
    private String information;
    
    private Category category;
    private SubjectDimension dimension;
    private User user;
    private int totalLessons;
    
    public Subject() {
    }
    
    public Subject(int subjectID, String thumbnail, String name, boolean featureFlag, boolean status, String information, Category category, SubjectDimension dimension, User user, int totalLessons) {
        this.subjectID = subjectID;
        this.thumbnail = thumbnail;
        this.name = name;
        this.featureFlag = featureFlag;
        this.status = status;
        this.information = information;
        this.category = category;
        this.dimension = dimension;
        this.user = user;
        this.totalLessons = totalLessons;
    }
    
    public Subject(int subjectID, String thumbnail, String name, boolean featureFlag, boolean status, String information, Category category, SubjectDimension dimension, User user) {
        this.subjectID = subjectID;
        this.thumbnail = thumbnail;
        this.name = name;
        this.featureFlag = featureFlag;
        this.status = status;
        this.information = information;
        this.category = category;
        this.dimension = dimension;
        this.user = user;
    }

    public Subject(int subjectID, PricePackage pricePackage, String thumbnail, String name, boolean featureFlag, boolean status, String information, Category category, SubjectDimension dimension) {
        this.subjectID = subjectID;
        this.pricePackage = pricePackage;
        this.thumbnail = thumbnail;
        this.name = name;
        this.featureFlag = featureFlag;
        this.status = status;
        this.information = information;
        this.category = category;
        this.dimension = dimension;
    }
    
    
//    public Subject(int subjectID, int pricePackageID, int dimensionID, String categoryName, String thumbnail, String name, boolean featureFlag, boolean status, String information) {
//        this.subjectID = subjectID;
//        this.pricePackageID = pricePackageID;
//        this.dimensionID = dimensionID;
//        this.categoryName = categoryName;
//        this.thumbnail = thumbnail;
//        this.name = name;
//        this.featureFlag = featureFlag;
//        this.status = status;
//        this.information = information;
//    }
    
    public Subject(int subjectID, PricePackage pricePackage, int dimensionID, String categoryName, String thumbnail, String name, boolean featureFlag, boolean status, String information) {
        this.subjectID = subjectID;
        this.pricePackage = pricePackage;
        this.dimensionID = dimensionID;
        this.categoryName = categoryName;
        this.thumbnail = thumbnail;
        this.name = name;
        this.featureFlag = featureFlag;
        this.status = status;
        this.information = information;
    }

    public Subject(int subjectID, PricePackage pricePackage, String thumbnail) {
        this.subjectID = subjectID;
        this.pricePackage = pricePackage;
        this.thumbnail = thumbnail;
    }
    
    

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

//    public int getPricePackageID() {
//        return pricePackageID;
//    }
//
//    public void setPricePackageID(int pricePackageID) {
//        this.pricePackageID = pricePackageID;
//    }
    public PricePackage getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(PricePackage pricePackage) {
        this.pricePackage = pricePackage;
    }

    public int getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(int dimensionID) {
        this.dimensionID = dimensionID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFeatureFlag() {
        return featureFlag;
    }

    public void setFeatureFlag(boolean featureFlag) {
        this.featureFlag = featureFlag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubjectDimension getDimension() {
        return dimension;
    }

    public void setDimension(SubjectDimension dimension) {
        this.dimension = dimension;
    }
    
    public boolean getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }
    
    
}
