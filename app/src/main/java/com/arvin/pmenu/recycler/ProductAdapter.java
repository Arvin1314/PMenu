package com.arvin.pmenu.recycler;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arvin.pmenu.R;
import com.arvin.pmenu.model.ProductModel;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.arvin.framework.core.image.ImageLoader;


public class ProductAdapter extends CommonAdapter<ProductModel> {

    private int mImageWidth;

    public ProductAdapter(List<ProductModel> data, int width) {
        super(R.layout.layout_recycler_product_item, data);
        mImageWidth = width;
    }

    @Override
    protected void convert(BaseViewHolder holder, ProductModel bean) {
        ImageView image = holder.getView(R.id.product_image);
        image.setLayoutParams(new RelativeLayout.LayoutParams(mImageWidth, mImageWidth));
        ImageLoader.showImage(bean.getPicture(), image);
        holder.setText(R.id.product_name, bean.getName());
    }
}
