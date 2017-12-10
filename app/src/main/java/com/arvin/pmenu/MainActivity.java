package com.arvin.pmenu;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arvin.pmenu.constants.Urls;
import com.arvin.pmenu.frame.CommonActivity;
import com.arvin.pmenu.model.CategoryModel;
import com.arvin.pmenu.model.ProductModel;
import com.arvin.pmenu.popupwindow.CategoryPopupWindow;
import com.arvin.pmenu.recycler.CommonAdapter;
import com.arvin.pmenu.recycler.ProductAdapter;
import com.arvin.pmenu.recycler.decoration.FlexibleDividerDecoration;
import com.arvin.pmenu.recycler.decoration.GridDecoration;
import com.arvin.pmenu.recycler.decoration.HorizontalDividerItemDecoration;
import com.arvin.pmenu.recycler.decoration.VerticalDividerItemDecoration;
import com.arvin.pmenu.response.DataResp;
import com.arvin.pmenu.response.callback.RequestCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.arvin.framework.bean.Screen;
import cn.arvin.framework.core.image.ImageLoader;
import cn.arvin.framework.utils.DensityUtil;


public class MainActivity extends CommonActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerProduct;
    private CategoryPopupWindow mCategoryPopupWindow;
    private CommonAdapter<ProductModel> mProductAdapter;
    private List<ProductModel> mAllProducts = new ArrayList<>();
    private List<ProductModel> mProducts = new ArrayList<>();

    private int mImageWidth;

    @Override
    public int getBodyViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBodyViews(View view) {
        initToolbar();

        mRecyclerProduct = (RecyclerView) findViewById(R.id.recycler_product);

        mRecyclerProduct.setLayoutManager(new GridLayoutManager(mActivity, 4));

        int space = DensityUtil.dp2px(12);

        mRecyclerProduct.addItemDecoration(new GridDecoration(space));

        mImageWidth = (Screen.WIDTH - 3 * space) / 4;

        //屏蔽动画
        ((SimpleItemAnimator) mRecyclerProduct.getItemAnimator()).setSupportsChangeAnimations(false);

        mProductAdapter = new ProductAdapter(mProducts, mImageWidth);

        mRecyclerProduct.setAdapter(mProductAdapter);
    }

    @Override
    protected void dealLogicAfterInitViews() {
        super.dealLogicAfterInitViews();
        initData();
    }


    @Override
    protected void setListeners() {
        mProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                List<ProductModel> newProducts = new ArrayList<>();
                for (ProductModel model : mAllProducts) {
                    if ("1".equals(model.getNewproduct())) {
                        newProducts.add(model);
                    }
                }
                DetailActivity.startAction(mActivity, mProducts.get(position), newProducts);
            }
        });
    }

    @Override
    protected void onClickEvent(int i, View view) {

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("全系列浮标");
        mToolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_color_default));
        //侧边栏的按钮
        mToolbar.setNavigationIcon(R.mipmap.ic_actionbar);
        //取代原本的actionbar
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click NavigationIcon");
                if (mCategoryPopupWindow != null && !mCategoryPopupWindow.isShowing()) {
                    mCategoryPopupWindow.showAsDropDown(mToolbar, 0, 0);
                }
            }
        });
    }


    private void initData() {
        objectGetRequest(Urls.HOME, DataResp.class, new RequestCallback<DataResp>() {
            @Override
            public void onSuccess(DataResp resp) {

                mAllProducts.clear();
                mAllProducts.addAll(resp.getProductinfolist());

                mProducts.clear();
                mProducts.addAll(mAllProducts);
                mProductAdapter.notifyDataSetChanged();

                mCategoryPopupWindow = new CategoryPopupWindow(mActivity)
                        .bindData(resp.getProductcataloglist())
                        .setOnItemClickListener(new CategoryPopupWindow.OnItemClickListener() {

                            @Override
                            public void onClick(String uuid, String name, String parent) {
                                mToolbar.setTitle(parent + " > " + name);
                                mProducts.clear();
                                for (ProductModel product : mAllProducts) {
                                    String id = product.getCataloguuid();
                                    if (!TextUtils.isEmpty(id)) {
                                        if (id.equals(uuid)) {
                                            mProducts.add(product);
                                        }
                                    }
                                }
                                mProductAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }
}
