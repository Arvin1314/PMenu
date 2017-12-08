package com.arvin.pmenu.model;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

    private String name;
    private String parent;
    private int sort;
    private int sortNo;
    private String isDeleted;
    private List<CategoryModel> subProductCatalog;

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

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<CategoryModel> getSubProductCatalog() {
        return subProductCatalog;
    }

    public void setSubProductCatalog(List<CategoryModel> subProductCatalog) {
        this.subProductCatalog = subProductCatalog;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", sort=" + sort +
                ", sortNo=" + sortNo +
                ", isDeleted='" + isDeleted + '\'' +
                ", subProductCatalog=" + subProductCatalog +
                '}';
    }
}
