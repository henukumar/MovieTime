package com.kalher.movietime.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.repository.MovieRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class DashboardViewModel extends ViewModel {

    private Executor mExecutor;

    // Contains livedata for list of movies & tv series according to uiActions
    private Map<UIAction, LiveData<PagedList<IShow>>> movieTypeLiveDataMap = new HashMap<>();

    public DashboardViewModel(){
    }

    public LiveData<PagedList<IShow>> getItemListLiveData(UIAction uiAction){
        if(!movieTypeLiveDataMap.containsKey(uiAction)){
            movieTypeLiveDataMap.put(uiAction, MovieRepo.getInstance().getShowList(uiAction));
        }
//        LiveData<List<? extends IShow>> itemListLiveData = movieTypeLiveDataMap.get(uiAction);

        switch (uiAction){
            case GET_POPULAR_MOVIES:
            case GET_NOW_PLAYING_MOVIES:
            case GET_TOP_RATED_MOVIES:
            case GET_UPCOMING_MOVIES:
//                Repository repository = new MovieRepo();
//                repository.getShowList(uiAction, itemListLiveData);
                return movieTypeLiveDataMap.get(uiAction);
        }
//        return itemListLiveData;
        return null;
    }

}
