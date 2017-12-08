package com.arvin.pmenu.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.arvin.pmenu.R;
import com.arvin.pmenu.model.CategoryModel;
import com.arvin.pmenu.recycler.CategoryHeaderAdapter;
import com.arvin.pmenu.recycler.decoration.HorizontalDividerItemDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.arvin.framework.bean.Screen;


public class CategoryPopupWindow extends PopupWindow {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private CategoryHeaderAdapter mAdapter;
    private List<CategoryModel> mCategory = new ArrayList<>();
    private OnItemClickListener mListener;

    public CategoryPopupWindow(Context context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_popup_category_menu, null);

        mRecyclerView = view.findViewById(R.id.popup_recycler_list);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .color(Color.WHITE)
                .size((int) (0.5 * Screen.SCALE))
                .build());


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        setContentView(view);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);

        setWidth((int) (250 * Screen.SCALE));
        setHeight((int) (Screen.HEIGHT - Screen.STATUS_BAR_HEIGHT - 48 * Screen.SCALE));

        setAnimationStyle(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public CategoryPopupWindow bindData(List<CategoryModel> list) {
        mCategory.clear();
        //大类
        for (int i = 0; i < list.size(); i++) {
            CategoryModel model = list.get(i);
            List<CategoryModel> subCategory = model.getSubProductCatalog();
            if (subCategory != null && subCategory.size() > 0) {
                for (CategoryModel sub : subCategory) {
                    sub.setSort(i);
                    sub.setParent(model.getName());
                    mCategory.add(sub);
                }
            } else {
                model.setSort(i);
                model.setParent(model.getName());
                mCategory.add(model);
            }
        }

        mAdapter = new CategoryHeaderAdapter();
        mAdapter.addAll(mCategory);

        mRecyclerView.setAdapter(mAdapter);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecyclerView.addItemDecoration(headersDecor);

        mAdapter.setOnItemClickListener(new CategoryHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String category = mCategory.get(position).getParent();
                int sortNo = mCategory.get(position).getSortNo();
                String name = mCategory.get(position).getName();
                mListener.onClick(category, sortNo, name);
                dismiss();
            }
        });
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(String category, int sortNo, String name);
    }

}
