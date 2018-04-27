package com.fan.perfectteaching.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BaseAdapterInterface<T> {

    protected List<T> mItems;
    protected Context mContext;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        mItems = new ArrayList<>();
    }

    public List<T> getList() {
        return mItems;
    }

    public void concatItems(List<T> items) {
        if (items == null) {
            return;
        }
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void refreshItems(List<T> items) {
        if (items == null) return;
        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
