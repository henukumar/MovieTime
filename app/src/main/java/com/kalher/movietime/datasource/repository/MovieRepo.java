package com.kalher.movietime.datasource.repository;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kalher.movietime.base.MovieTime;
import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.pageloader.MovieDataSource;
import com.kalher.movietime.datasource.source.MovieSourceDB;
import com.kalher.movietime.datasource.source.MovieSourceNetwork;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class MovieRepo extends Repository{

    private static Map<UIAction, MediatorLiveData> mediatorLiveDataMap = new HashMap<>();

    private static MovieRepo instance = null;

    private MovieSourceDB movieSourceDB;
    private MovieSourceNetwork movieSourceNetwork;

    public static MovieRepo getInstance(){
        if(instance == null){
            instance = new MovieRepo();
        }
        return instance;
    }

    private MovieRepo(){
        movieSourceDB = new MovieSourceDB();
        movieSourceNetwork = new MovieSourceNetwork();
    }

    public LiveData<PagedList<IShow>> getShowList(UIAction uiAction){
        if(!mediatorLiveDataMap.containsKey(uiAction)){
            mediatorLiveDataMap.put(uiAction, new MediatorLiveData<>());
            initializeShowListLiveData(uiAction);
        }
        return mediatorLiveDataMap.get(uiAction);
    }

    // fetch movies from net & insert in db
    private void initializeShowListLiveData(UIAction uiAction){
        final int[] page = {1};

        PagedList.BoundaryCallback<IShow> boundaryCallback = new PagedList.BoundaryCallback<IShow>() {
            @Override
            public void onZeroItemsLoaded() {
                super.onZeroItemsLoaded();
                Timber.i("TrackPaging" + uiAction + " onZeroItemsLoaded");
//                MovieSourceNetwork.getInstance().fetchShowListFromApi(uiAction, page[0]);
            }

            @Override
            public void onItemAtFrontLoaded(@NonNull IShow itemAtFront) {
                super.onItemAtFrontLoaded(itemAtFront);
                Timber.i("TrackPaging" + uiAction + " onItemAtFrontLoaded");
            }

            @Override
            public void onItemAtEndLoaded(@NonNull IShow itemAtEnd) {
                super.onItemAtEndLoaded(itemAtEnd);
                Timber.i("TrackPaging" + uiAction + " onItemAtEndLoaded");
//                MovieSourceNetwork.getInstance().fetchShowListFromApi(uiAction, page[0]++);
            }
        };

        Observer mediatorLiveDataObserver = new Observer() {
            @Override
            public void onChanged(Object o) {

                mediatorLiveDataMap.get(uiAction).setValue(o);
//                mediatorLiveDataMap.get(uiAction).removeSource(MovieSourceDB.getMovies(uiAction));
            }
        };
//        mediatorLiveDataMap.get(uiAction).addSource(MovieSourceDB.getMovies(uiAction, boundaryCallback), mediatorLiveDataObserver);
        mediatorLiveDataMap.get(uiAction).addSource(MovieSourceNetwork.getInstance().getMovies(uiAction, boundaryCallback), mediatorLiveDataObserver);
    }

}
/*
* # Check last update timestamp if older than 24 hrs, clear data from db
* # if db contains data Fetch & show movies list from db
* # On
* */
