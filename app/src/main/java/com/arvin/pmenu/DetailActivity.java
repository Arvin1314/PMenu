package com.arvin.pmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.arvin.pmenu.frame.CommonActivity;
import com.arvin.pmenu.model.ProductModel;
import com.arvin.pmenu.recycler.CommonAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.arvin.framework.core.image.ImageLoader;


public class DetailActivity extends CommonActivity {
    private static final String PRODUCT_MODEL = "productmodel";
    private static final String PRODUCT_COMMEND = "product_commend";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerCommend;
    private CommonAdapter<ProductModel> mCommendAdapter;
    private ImageView mVideo;

    private ProductModel mProduct;
    private ArrayList<ProductModel> mCommends;

    public static void startAction(Context context, ProductModel product, List<ProductModel> commends) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PRODUCT_MODEL, product);
        intent.putExtra(PRODUCT_COMMEND, (Serializable) commends);
        context.startActivity(intent);
    }

    @Override
    public int getBodyViewId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void dealLogicBeforeInitViews() {
        super.dealLogicBeforeInitViews();
        mProduct = (ProductModel) getIntent().getSerializableExtra(PRODUCT_MODEL);
        mCommends = (ArrayList<ProductModel>) getIntent().getSerializableExtra(PRODUCT_COMMEND);
    }

    @Override
    public void initBodyViews(View view) {
        initToolbar();

        mVideo = (ImageView) findViewById(R.id.product_video);
        mRecyclerCommend = (RecyclerView) findViewById(R.id.recycler_commend);

        mRecyclerCommend.setLayoutManager(new LinearLayoutManager(this));
        //屏蔽动画
        ((SimpleItemAnimator) mRecyclerCommend.getItemAnimator()).setSupportsChangeAnimations(false);

        mCommendAdapter = new CommonAdapter<ProductModel>(R.layout.layout_recycler_product_item, mCommends) {
            @Override
            protected void convert(BaseViewHolder holder, ProductModel bean) {
                ImageView image = holder.getView(R.id.product_image);
                ImageLoader.showImage(bean.getPicture(), image);
                holder.setText(R.id.product_name, bean.getName());
            }
        };

        mRecyclerCommend.setAdapter(mCommendAdapter);

        String videoUrl = mProduct.getVideoDesc();
        mVideo.setVisibility(!TextUtils.isEmpty(videoUrl) ? View.VISIBLE : View.GONE);

    }

    @Override
    protected void setListeners() {
        mVideo.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(int id, View view) {
        switch (id) {
            case R.id.product_video:
                Uri videoUri = Uri.parse(mProduct.getVideoDesc());
                String type = "video/*";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(videoUri, type);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(mProduct.getName());
        mToolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        mToolbar.setTitleTextColor(Color.WHITE);
        //侧边栏的按钮
        mToolbar.setNavigationIcon(R.mipmap.common_arrows_left_white);
        //取代原本的actionbar
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

    }


}
