package com.kalher.movietime.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;

import com.kalher.movietime.R;
import com.kalher.movietime.base.BaseRVAdapter;
import com.kalher.movietime.base.BaseViewHolder;
import com.kalher.movietime.common.interfaces.IRecyclerItem;
import com.kalher.movietime.dashboard.viewholder.MovieItemViewHolder;
import com.kalher.movietime.databinding.ItemOverviewBinding;
import com.kalher.movietime.base.BaseViewHolder.ItemViewClickListener;

import java.util.List;

public class ItemListAdapter extends BaseRVAdapter {

    // Generic list List<?>, Generic list of type with IRecyclerItem base class
    public ItemListAdapter(ItemViewClickListener itemSelectedListener,
                           @NonNull DiffUtil.ItemCallback<? extends IRecyclerItem> diffCallback){
        super(itemSelectedListener, diffCallback);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemOverviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_overview, parent, false);
        BaseViewHolder viewHolder = new MovieItemViewHolder(binding, mItemViewClickListener);
        return viewHolder;
//        return super.onCreateViewHolder(parent, viewType);
    }
}
