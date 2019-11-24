package com.kalher.movietime.datasource.source;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.kalher.movietime.base.MovieTime;
import com.kalher.movietime.common.AppConfig;
import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.datasource.ModelToEntityConverter;
import com.kalher.movietime.datasource.datasourcefactory.MovieDataSourceFactory;
import com.kalher.movietime.datasource.db.DatabaseFactory;
import com.kalher.movietime.datasource.model.movie.ShowListResult;
import com.kalher.movietime.datasource.webservice.ApiClient;
import com.kalher.movietime.datasource.webservice.url.WebServiceURL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MovieSourceNetwork {

    private String TAG = this.getClass().getSimpleName();

    private static Map<UIAction, LiveData<PagedList<IShow>>> pagedListLiveDataMap = new HashMap<>();

    private static MovieSourceNetwork instance = null;

    public static MovieSourceNetwork getInstance(){
        if(instance == null){
            instance = new MovieSourceNetwork();
        }
        return instance;
    }

    public void fetchShowListFromApi(UIAction uiAction, int page,
                                     PageKeyedDataSource.LoadInitialCallback<Integer, IShow> initialCallback,
                                     PageKeyedDataSource.LoadParams<Integer> params,
                                     PageKeyedDataSource.LoadCallback<Integer, IShow> callback){
        Timber.i(TAG, "TrackPaging", "fetchShowListFromApi for UIAction:-" + uiAction);
        WebServiceURL webServiceURL = uiAction.getActionUrl();
        ApiClient.getTMDBClient()
                .getItemList(webServiceURL.getEndPoint(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShowListResult>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ShowListResult movieListResult) {
                        Timber.i(TAG, "TrackPaging", "fetchShowListFromApi onNext for UIAction:-" + uiAction);
//                        insertMovieListInDb(movieListResult);
//                        ((MutableLiveData) showListLiveData).setValue(movieListResult.getResults());
                        insertMovieListInDb(movieListResult, uiAction, initialCallback, params, callback);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getCause();

                        e.getMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void insertMovieListInDb(final ShowListResult movieListResult, UIAction uiAction,
                                     PageKeyedDataSource.LoadInitialCallback<Integer, IShow> initialCallback,
                                     PageKeyedDataSource.LoadParams<Integer> params,
                                     PageKeyedDataSource.LoadCallback<Integer, IShow> callback) {
        Timber.i(TAG, "TrackPaging", "insertMovieListInDb");
        Completable movieListInsertionCompletable = Completable.fromAction(() -> DatabaseFactory.getDbInstance().movieDao()
                .insertMovieList(ModelToEntityConverter.getMovieEntityListFromMovieList(movieListResult.getResults(), movieListResult.getPage(), uiAction.name())));

//        Completable movieTagInsertionCompletable = Completable.fromAction(() -> DatabaseFactory.getDbInstance().movieTagDao()
//                .insertRecordList(ModelToEntityConverter.getMovieTagEntityList(movieListResult, "")));

        movieListInsertionCompletable
//                .andThen(movieTagInsertionCompletable)
                .subscribeOn(Schedulers.io())   // run on separate background thread
                .observeOn(AndroidSchedulers.mainThread())  // return callback on main(UI) thread
                .subscribe();
//                        new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Timber.i(TAG, "TrackPaging onSubscribe MovieListInsertionCompletable");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Timber.i(TAG, "TrackPaging onComplete MovieListInsertionCompletable");
//                        int page = 1;
//                        if(initialCallback == null){
//                            page = params.key;
//                        }
//                        List<IShow> movieList = new ArrayList<>(EntityToModelConverter.
//                                getMovieListFromMovieEntityList(DatabaseFactory.getDbInstance().movieDao().getMovieByPage(page)));
//                        if(initialCallback != null){
//                            initialCallback.onResult(movieList, page, page+1);
//                        }
//                        if(callback != null){
//                            callback.onResult(movieList, page+1);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Timber.i(TAG, "TrackPaging onError MovieListInsertionCompletable");
//                    }
//                });

    }

    private static Map<UIAction, DataSource> dataSourceMap = new HashMap<>();

    public static LiveData<PagedList<IShow>> getMovies(UIAction uiAction, PagedList.BoundaryCallback<IShow> boundaryCallback){
        return buildAndGetPagedList(uiAction, boundaryCallback);
    }

    public static LiveData<PagedList<IShow>> buildAndGetPagedList(UIAction uiAction, PagedList.BoundaryCallback<IShow> boundaryCallback){
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(uiAction);
        if(!dataSourceMap.containsKey(uiAction)){
            dataSourceMap.put(uiAction, movieDataSourceFactory.create());
        }

        if(!pagedListLiveDataMap.containsKey(uiAction)){
            LiveData<PagedList<IShow>> pagedListLiveData =
                    new LivePagedListBuilder<>(movieDataSourceFactory, getPagedListConfig())
                            .setFetchExecutor(MovieTime.getCommonExecutor())
                            .setBoundaryCallback(boundaryCallback)
                            .build();
            pagedListLiveDataMap.put(uiAction, pagedListLiveData);
        }

        return pagedListLiveDataMap.get(uiAction);
    }

    private static PagedList.Config getPagedListConfig(){
        PagedList.Config.Builder pagedListConfigBuilder = new PagedList.Config.Builder();
        pagedListConfigBuilder
                .setEnablePlaceholders(AppConfig.MovieConfig.SHOW_PLACEHOLDER)
                .setInitialLoadSizeHint(AppConfig.MovieConfig.INITIAL_LOAD_SIZE_HINT)
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
