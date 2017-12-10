package com.arvin.pmenu.model;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

    private String name;
    private String parent;
    private int sort;
    private String isdeleted;
    private String uuid;
    private List<CategoryModel> subproductcatalog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CategoryModel> getSubproductcatalog() {
        return subproductcatalog;
    }

    public void setSubproductcatalog(List<CategoryModel> subproductcatalog) {
        this.subproductcatalog = subproductcatalog;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", sort=" + sort +
                ", isdeleted='" + isdeleted + '\'' +
                ", uuid='" + uuid + '\'' +
                ", subproductcatalog=" + subproductcatalog +
                '}';
    }
}
