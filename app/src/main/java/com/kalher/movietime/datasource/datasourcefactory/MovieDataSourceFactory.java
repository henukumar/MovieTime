package com.kalher.movietime.datasource.datasourcefactory;

import android.provider.Contacts;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.pageloader.MovieDataSource;

public class MovieDataSourceFactory extends MovieDataSource.Factory<Integer, IShow> {

    private MutableLiveData<MovieDataSource> movieDataSourceLiveData = new MutableLiveData<>();

    private MovieDataSource movieDataSource;
    private UIAction uiAction;

    public MovieDataSourceFactory(UIAction uiAction){
        this.uiAction = uiAction;
    }

    @NonNull
    @Override
    public MovieDataSource create() {
        movieDataSource = new MovieDataSource(uiAction);
        movieDataSourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

}
