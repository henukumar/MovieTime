package com.kalher.movietime.datasource.pageloader;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.EntityToModelConverter;
import com.kalher.movietime.datasource.db.DatabaseFactory;
import com.kalher.movietime.datasource.source.MovieSourceNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MovieDataSource extends PageKeyedDataSource<Integer, IShow> {

    private UIAction uiAction;

    public MovieDataSource(UIAction uiAction) {
        this.uiAction = uiAction;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, IShow> callback) {
        Timber.i("TrackPaging" + uiAction + " loadInitial");
        List<IShow> movieList = new ArrayList<>(EntityToModelConverter.
                getMovieListFromMovieEntityList(DatabaseFactory.getDbInstance().movieDao().getMovieByPage(1, uiAction.name())));
        if (movieList != null && movieList.size() > 0) {
            callback.onResult(movieList, 1, 2);
        } else {
//            callback.onResult(movieList, 1, 1); // boundary callback onZeroItemsLoaded will be called
            MovieSourceNetwork.getInstance().fetchShowListFromApi(uiAction, 1, callback, null, null); //now boundary callback won't be used anymore
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, IShow> callback) {
        Timber.i("TrackPaging" + uiAction + " loadBefore");
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, IShow> callback) {
        Timber.i("TrackPaging" + uiAction + " loadAfter");
        List<IShow> movieList = new ArrayList<>(EntityToModelConverter.
                getMovieListFromMovieEntityList(DatabaseFactory.getDbInstance().movieDao().getMovieByPage(params.key, uiAction.name())));
        if (movieList != null && movieList.size() > 0) {
            callback.onResult(movieList, params.key + 1);
        } else {
//            callback.onResult(movieList, params.key);   // boundary callback onItemAtEndLoaded will be called
            MovieSourceNetwork.getInstance().fetchShowListFromApi(uiAction, params.key, null, params, callback);  //now boundary callback won't be used anymore
        }
    }

}
