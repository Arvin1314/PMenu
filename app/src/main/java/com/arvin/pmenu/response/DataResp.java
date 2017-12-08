package com.arvin.pmenu.response;

import com.arvin.pmenu.model.CategoryModel;
import com.arvin.pmenu.model.ProductModel;

import java.util.List;

/**
 * Created by Arvin on 2017/12/4.
 */

public class DataResp extends BaseResp {
    private List<CategoryModel> productCataloglist;
    private List<ProductModel> productInfolist;

    public List<CategoryModel> getProductCataloglist() {
        return productCataloglist;
    }

    public void setProductCataloglist(List<CategoryModel> productCataloglist) {
        this.productCataloglist = productCataloglist;
    }

    public List<ProductModel> getProductInfolist() {
        return productInfolist;
    }

    public void setProductInfolist(List<ProductModel> productInfolist) {
        this.productInfolist = productInfolist;
    }

    @Override
    public String toString() {
        return "DataResp{" +
                "productCataloglist=" + productCataloglist +
                ", productInfolist=" + productInfolist +
                '}';
    }
}
