package com.arvin.pmenu.recycler;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    protected CommonAdapter(int layoutId, List<T> data) {
        super(layoutId, data);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return super.getRecyclerView();
    }

    @Override
    protected abstract void convert(BaseViewHolder holder, T bean);
}
