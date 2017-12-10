package com.arvin.pmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvin.pmenu.frame.CommonActivity;
import com.arvin.pmenu.model.ProductModel;
import com.arvin.pmenu.recycler.CommonAdapter;
import com.arvin.pmenu.recycler.ProductAdapter;
import com.arvin.pmenu.recycler.decoration.HorizontalDividerItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.arvin.framework.bean.Screen;
import cn.arvin.framework.core.image.ImageLoader;
import cn.arvin.framework.utils.DensityUtil;


public class DetailActivity extends CommonActivity {
    private static final String PRODUCT_MODEL = "productmodel";
    private static final String PRODUCT_COMMEND = "product_commend";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerCommend;
    private CommonAdapter<ProductModel> mCommendAdapter;
    private ImageView mProductImage;
    private TextView mProductDesc;
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

        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductDesc = (TextView) findViewById(R.id.product_desc);
        mVideo = (ImageView) findViewById(R.id.product_video);
        mRecyclerCommend = (RecyclerView) findViewById(R.id.recycler_commend);

        mProductImage.setLayoutParams(new LinearLayout.LayoutParams(Screen.WIDTH / 4, Screen.WIDTH / 4));

        ImageLoader.showImage(mProduct.getPicturedesc(), mProductImage);

        mProductDesc.setText(Html.fromHtml(mProduct.getTextdesc()));

        initCommendsView();

        String videoUrl = mProduct.getVideodesc();
        mVideo.setVisibility(!TextUtils.isEmpty(videoUrl) ? View.VISIBLE : View.GONE);

    }

    private void initCommendsView() {
        mRecyclerCommend.setLayoutParams(new LinearLayout.LayoutParams(Screen.WIDTH / 4, ViewGroup.LayoutParams.MATCH_PARENT));

        mRecyclerCommend.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mActivity)
                .size(DensityUtil.dp2px(12))
                .color(Color.WHITE)
                .build());

        mRecyclerCommend.setLayoutManager(new LinearLayoutManager(this));
        //屏蔽动画
        ((SimpleItemAnimator) mRecyclerCommend.getItemAnimator()).setSupportsChangeAnimations(false);

        mCommendAdapter = new ProductAdapter(mCommends, Screen.WIDTH / 4);

        mRecyclerCommend.setAdapter(mCommendAdapter);
    }

    @Override
    protected void setListeners() {
        mVideo.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(int id, View view) {
        switch (id) {
            case R.id.product_video:
                Uri videoUri = Uri.parse(mProduct.getVideodesc());
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
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_color_default));
        //侧边栏的按钮
        mToolbar.setNavigationIcon(R.mipmap.common_arrows_left);
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
