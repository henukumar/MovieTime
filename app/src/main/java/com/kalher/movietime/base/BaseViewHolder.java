package com.kalher.movietime.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kalher.movietime.common.interfaces.IRecyclerItem;

import java.util.List;

public abstract class BaseViewHolder<T extends IRecyclerItem> extends RecyclerView.ViewHolder {

    protected ItemViewClickListener mItemClickListener;
    private List<T> mItemList;

    public BaseViewHolder(View itemView, ItemViewClickListener itemClickListener) {
        super(itemView);
        mItemClickListener = itemClickListener;
    }

    protected abstract void bind(T data);

    public interface ItemViewClickListener{
        void itemClicked(View view);
    }

}
