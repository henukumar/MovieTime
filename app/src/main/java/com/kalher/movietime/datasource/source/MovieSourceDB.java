package com.kalher.movietime.datasource.source;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kalher.movietime.base.MovieTime;
import com.kalher.movietime.common.AppConfig;
import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.datasourcefactory.MovieDataSourceFactory;
import com.kalher.movietime.datasource.db.DatabaseFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MovieSourceDB {

    private static Map<UIAction, DataSource> dataSourceMap = new HashMap<>();

    public static LiveData<PagedList<IShow>> getMovies(UIAction uiAction, PagedList.BoundaryCallback<IShow> boundaryCallback){
        return buildAndGetPagedList(uiAction, boundaryCallback);
    }

    public static LiveData<PagedList<IShow>> buildAndGetPagedList(UIAction uiAction, PagedList.BoundaryCallback<IShow> boundaryCallback){
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(uiAction);
        if(!dataSourceMap.containsKey(uiAction)){
            dataSourceMap.put(uiAction, movieDataSourceFactory.create());
        }

        LiveData<PagedList<IShow>> pagedListLiveData =
                new LivePagedListBuilder<>(movieDataSourceFactory, getPagedListConfig())
                        .setFetchExecutor(MovieTime.getCommonExecutor())
                        .setBoundaryCallback(boundaryCallback)
                        .build();
        return pagedListLiveData;
    }

    private static PagedList.Config getPagedListConfig(){
        PagedList.Config.Builder pagedListConfigBuilder = new PagedList.Config.Builder();
        pagedListConfigBuilder
                .setEnablePlaceholders(AppConfig.MovieConfig.SHOW_PLACEHOLDER)
                .setInitialLoadSizeHint(AppConfig.MovieConfig.ITEM_IN_ONE_PAGE)
                .setPageSize(AppConfig.MovieConfig.ITEM_IN_ONE_PAGE)
                .setPrefetchDistance(AppConfig.MovieConfig.PREFETCH_DISTANCE);
        return pagedListConfigBuilder.build();
    }

    private static void invalidateDataSource(){
        Iterator<UIAction> iterator = dataSourceMap.keySet().iterator();
        while (iterator.hasNext()){
            dataSourceMap.get(iterator.next()).invalidate();
        }
    }

}
