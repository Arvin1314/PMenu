package com.arvin.pmenu.recycler.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


public class GridDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public GridDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            int position = parent.getChildAdapterPosition(view);
            int childCount = parent.getAdapter().getItemCount();

            boolean isFirstHorizontal = (position + 1) % spanCount == 1;
            boolean isLastHorizontal = (position + 1) % spanCount == 0;
            boolean isFirstVertical = position / spanCount == 0;
            boolean isLastVertical = position / spanCount == childCount / spanCount - 1;
            outRect.left = space;
            outRect.top = 0;
            outRect.right = isLastHorizontal ? space : 0;
            outRect.bottom = space;
        }
    }
}