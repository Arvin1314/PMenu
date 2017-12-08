package com.arvin.pmenu.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvin.pmenu.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

public class CategoryHeaderAdapter extends CategoryAdapter<CategoryHeaderAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<CategoryHeaderAdapter.HeaderViewHolder> {

    public CategoryHeaderAdapter() {
    }

    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_category_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(getItem(position).getName());
        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
        }
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSort();
    }



    @Override
    public CategoryHeaderAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_category_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(CategoryHeaderAdapter.HeaderViewHolder holder, int position) {
        holder.header.setText(String.valueOf(getItem(position).getParent()));
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;

        HeaderViewHolder(View view) {
            super(view);
            header = view.findViewById(R.id.category_header);
        }
    }
}