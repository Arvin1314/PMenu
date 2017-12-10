package com.arvin.pmenu.model;

import java.io.Serializable;

/**
 * Created by Arvin on 2017/12/4.
 */

public class ProductModel implements Serializable {


    private String uuid;
    private String name;
    private String cataloguuid;
    private String isdeleted;
    private String newproduct;
    private String picture;
    private String picturedesc;
    private String textdesc;
    private String videodesc;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCataloguuid() {
        return cataloguuid;
    }

    public void setCataloguuid(String cataloguuid) {
        this.cataloguuid = cataloguuid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getNewproduct() {
        return newproduct;
    }

    public void setNewproduct(String newproduct) {
        this.newproduct = newproduct;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicturedesc() {
        return picturedesc;
    }

    public void setPicturedesc(String picturedesc) {
        this.picturedesc = picturedesc;
    }

    public String getTextdesc() {
        return textdesc;
    }

    public void setTextdesc(String textdesc) {
        this.textdesc = textdesc;
    }

    public String getVideodesc() {
        return videodesc;
    }

    public void setVideodesc(String videodesc) {
        this.videodesc = videodesc;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", cataloguuid='" + cataloguuid + '\'' +
                ", isdeleted='" + isdeleted + '\'' +
                ", newproduct='" + newproduct + '\'' +
                ", picture='" + picture + '\'' +
                ", picturedesc='" + picturedesc + '\'' +
                ", textdesc='" + textdesc + '\'' +
                ", videodesc='" + videodesc + '\'' +
                '}';
    }
}
