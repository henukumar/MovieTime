package com.kalher.movietime.datasource.webservice;

import com.kalher.movietime.datasource.model.movie.ShowListResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface TMDBWebservice {

    String BASE_URL = "https://api.themoviedb.org/";
    int API_VERSION = 3;
    String BASE_VERSION_URL = BASE_URL + API_VERSION + "/";

    @GET
    Observable<ShowListResult> getItemList(@Url String url, @Query("page") int page);

    @GET("movie/{category}")
    Observable<ShowListResult> movieList(@Path("category") String category, @QueryMap Map<String, String> options);

    @GET("group/{id}/users")
    Observable<List<String>> movieDetail(@Path("id") int groupId, @QueryMap Map<String, String> options);
}
