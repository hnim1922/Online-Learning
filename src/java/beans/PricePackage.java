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
public class PricePackage implements Serializable {

    private int pricePackageID;
    private String name;
    private int accessDuration;
    private String status;
    private float listPrice;
    private float salePrice;
    private String description;
    private int subjectID;

    public PricePackage() {
    }

    public PricePackage(int pricePackageID, float salePrice) {
        this.pricePackageID = pricePackageID;
        this.salePrice = salePrice;
    }

    public PricePackage(int pricePackageID, String name, int accessDuration, String status, float listPrice, float salePrice, String description, int subjectID) {
        this.pricePackageID = pricePackageID;
        this.name = name;
        this.accessDuration = accessDuration;
        this.status = status;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.description = description;
        this.subjectID = subjectID;
    }

    public PricePackage(int pricePackageID, String name, int accessDuration, String status, float listPrice, float salePrice, String description) {
        this.pricePackageID = pricePackageID;
        this.name = name;
        this.accessDuration = accessDuration;
        this.status = status;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.description = description;
    }

    public int getPricePackageID() {
        return pricePackageID;
    }

    public void setPricePackageID(int pricePackageID) {
        this.pricePackageID = pricePackageID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessDuration() {
        return accessDuration;
    }

    public void setAccessDuration(int accessDuration) {
        this.accessDuration = accessDuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
