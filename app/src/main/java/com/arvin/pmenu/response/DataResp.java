package com.arvin.pmenu.response;

import com.arvin.pmenu.model.CategoryModel;
import com.arvin.pmenu.model.ProductModel;

import java.util.List;

/**
 * Created by Arvin on 2017/12/4.
 */

public class DataResp extends BaseResp {
    private List<CategoryModel> productcataloglist;
    private List<ProductModel> productinfolist;

    public List<CategoryModel> getProductcataloglist() {
        return productcataloglist;
    }

    public void setProductcataloglist(List<CategoryModel> productcataloglist) {
        this.productcataloglist = productcataloglist;
    }

    public List<ProductModel> getProductinfolist() {
        return productinfolist;
    }

    public void setProductinfolist(List<ProductModel> productinfolist) {
        this.productinfolist = productinfolist;
    }

    @Override
    public String toString() {
        return "DataResp{" +
                "productcataloglist=" + productcataloglist +
                ", productinfolist=" + productinfolist +
                '}';
    }
}
