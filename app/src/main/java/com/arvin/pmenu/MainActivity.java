package com.arvin.pmenu;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.arvin.pmenu.constants.Urls;
import com.arvin.pmenu.frame.CommonActivity;
import com.arvin.pmenu.model.CategoryModel;
import com.arvin.pmenu.model.ProductModel;
import com.arvin.pmenu.popupwindow.CategoryPopupWindow;
import com.arvin.pmenu.recycler.CommonAdapter;
import com.arvin.pmenu.response.DataResp;
import com.arvin.pmenu.response.callback.RequestCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.arvin.framework.core.image.ImageLoader;


public class MainActivity extends CommonActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerProduct;
    private CategoryPopupWindow mCategoryPopupWindow;
    private CommonAdapter<ProductModel> mProductAdapter;
    private List<ProductModel> mAllProducts = new ArrayList<>();
    private List<ProductModel> mProducts = new ArrayList<>();

    @Override
    public int getBodyViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBodyViews(View view) {
        initToolbar();

        mRecyclerProduct = (RecyclerView) findViewById(R.id.recycler_product);
        mRecyclerProduct.setLayoutManager(new GridLayoutManager(mActivity, 4));
        //屏蔽动画
        ((SimpleItemAnimator) mRecyclerProduct.getItemAnimator()).setSupportsChangeAnimations(false);

        mProductAdapter = new CommonAdapter<ProductModel>(R.layout.layout_recycler_product_item, mProducts) {
            @Override
            protected void convert(BaseViewHolder holder, ProductModel bean) {
                ImageView image = holder.getView(R.id.product_image);
                ImageLoader.showImage(bean.getPicture(), image);
                holder.setText(R.id.product_name, bean.getName());
            }
        };

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
                DetailActivity.startAction(mActivity, mProducts.get(position),mProducts);
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
        mToolbar.setTitleTextColor(Color.WHITE);
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
        objectGetRequest(Urls.HOME, String.class, new RequestCallback<String>() {
            @Override
            public void onSuccess(String dataResp) {

                try {
                    String utf = new String(dataResp.getBytes("iso8859-1"), "utf-8");
                    DataResp resp = new Gson().fromJson(utf, DataResp.class);
                    System.out.println("utf-->" + utf);

                    System.out.println("resp-->" + resp.toString());
                    System.out.println("resp.getProductInfolist()-->" + resp.getProductInfolist().size());

                    mAllProducts.clear();
                    for (int i = 0; i < 10; i++) {
                        mAllProducts.addAll(resp.getProductInfolist());
                    }

                    mProducts.clear();
                    mProducts.addAll(mAllProducts);
                    mProductAdapter.notifyDataSetChanged();

                    mCategoryPopupWindow = new CategoryPopupWindow(mActivity);
                    List<CategoryModel> list = resp.getProductCataloglist();
                    List<CategoryModel> tempList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        tempList.addAll(list);
                    }
                    mCategoryPopupWindow.bindData(tempList)
                            .setOnItemClickListener(new CategoryPopupWindow.OnItemClickListener() {
                                @Override
                                public void onClick(String category, int sortNo, String name) {
                                    System.out.println(category + "--" + sortNo + "--" + name);
                                    mToolbar.setTitle(category + " > " + name);
                                    //notify 数据
                                    mProducts.clear();
                                    System.out.println(mAllProducts.size());
                                    for (ProductModel model : mAllProducts) {
                                        System.out.println("for-->" + model.getSortNo());
                                        if (model.getSortNo() == sortNo) {
                                            mProducts.add(model);
                                        }
                                    }
                                    System.out.println("mProducts-->" + mProducts.size());
                                    mProductAdapter.notifyDataSetChanged();
                                }
                            });

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
