package com.kalher.movietime.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.kalher.movietime.base.BaseViewHolder.ItemViewClickListener;
import com.kalher.movietime.common.interfaces.IRecyclerItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRVAdapter<T extends IRecyclerItem> extends PagedListAdapter<T, BaseViewHolder> {

    protected List<T> mDataList;
    protected ItemViewClickListener mItemViewClickListener;

    public BaseRVAdapter(ItemViewClickListener itemViewClickListener,
                         @NonNull DiffUtil.ItemCallback<T> diffCallback){
        super(diffCallback);
//        mDataList = dataList;
        mItemViewClickListener = itemViewClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

//    @Override
//    public int getItemCount() {
//        return mDataList != null ? mDataList.size() : 0;
//    }

//    @Override
//    public void submitList(@Nullable PagedList<T> pagedList) {
//        super.submitList(pagedList != null ? new PagedList<T>(pagedList) : null);
//    }

}
