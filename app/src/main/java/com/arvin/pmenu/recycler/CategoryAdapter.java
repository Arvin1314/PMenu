package com.arvin.pmenu.recycler;

import android.support.v7.widget.RecyclerView;

import com.arvin.pmenu.model.CategoryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Adapter holding a list of animal names of type String. Note that each item must be unique.
 */
public abstract class CategoryAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private List<CategoryModel> items = new ArrayList<>();

  public CategoryAdapter() {
    setHasStableIds(true);
  }



  public void addAll(Collection<? extends CategoryModel> collection) {
    if (collection != null) {
      items.addAll(collection);
      notifyDataSetChanged();
    }
  }


  public CategoryModel getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).hashCode();
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
