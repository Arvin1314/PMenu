package com.arvin.pmenu.model;

import java.io.Serializable;

/**
 * Created by Arvin on 2017/12/4.
 */

public class ProductModel implements Serializable {

    private int sortNo;
    private String name;

    private String isDeleted;
    private String newProduct;
    private String picture;
    private String pictureDesc;
    private String textDesc;
    private String videoDesc;

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(String newProduct) {
        this.newProduct = newProduct;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureDesc() {
        return pictureDesc;
    }

    public void setPictureDesc(String pictureDesc) {
        this.pictureDesc = pictureDesc;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "sortNo=" + sortNo +
                ", name='" + name + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", newProduct='" + newProduct + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureDesc='" + pictureDesc + '\'' +
                ", textDesc='" + textDesc + '\'' +
                ", videoDesc='" + videoDesc + '\'' +
                '}';
    }
}
