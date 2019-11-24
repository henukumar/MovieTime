package com.kalher.movietime.datasource.pageloader;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.kalher.movietime.common.interfaces.IShow;

public class TVDataSource extends PageKeyedDataSource<Integer, IShow> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, IShow> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, IShow> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, IShow> callback) {

    }

}
