package com.kalher.movietime.dashboard.viewholder;

import androidx.databinding.ViewDataBinding;

import com.kalher.movietime.base.BaseViewHolder;
import com.kalher.movietime.common.interfaces.IRecyclerItem;
import com.kalher.movietime.databinding.ItemOverviewBinding;
import com.kalher.movietime.datasource.model.movie.Movie;

public class MovieItemViewHolder extends BaseViewHolder {

    ItemOverviewBinding mBinding;

    public MovieItemViewHolder(ViewDataBinding binding, ItemViewClickListener itemViewClickListener) {
        super(binding.getRoot(), itemViewClickListener);
        mBinding = (ItemOverviewBinding) binding;
    }

    @Override
    protected void bind(IRecyclerItem itemData){
        Movie movie = (Movie) itemData;
        mBinding.sdvItemPoster.setImageURI("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath());
        mBinding.tvMovieTitle.setText(movie.getTitle());
    }

}
