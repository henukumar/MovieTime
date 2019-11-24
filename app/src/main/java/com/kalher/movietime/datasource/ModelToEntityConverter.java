package com.kalher.movietime.datasource;

import com.kalher.movietime.datasource.db.entity.MovieEntity;
import com.kalher.movietime.datasource.db.entity.MovieTagEntity;
import com.kalher.movietime.datasource.model.movie.Movie;
import com.kalher.movietime.datasource.model.movie.ShowListResult;

import java.util.ArrayList;
import java.util.List;

public class ModelToEntityConverter {

    public static List<MovieEntity> getMovieEntityListFromMovieList(List<Movie> movieList){
        return getMovieEntityListFromMovieList(movieList, 0, "");
    }

    public static List<MovieEntity> getMovieEntityListFromMovieList(List<Movie> movieList, int page, String tag){
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for(Movie i : movieList){
            if(page > 0){
                movieEntityList.add(getMovieEntityFromMovie(i, page, tag));
            }else {
                movieEntityList.add(getMovieEntityFromMovie(i));
            }
        }
        return movieEntityList;
    }

    public static MovieEntity getMovieEntityFromMovie(Movie movie){
        return getMovieEntityFromMovie(movie, 0, "");
    }

    public static MovieEntity getMovieEntityFromMovie(Movie movie, int page, String tag){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPageNumber(page);
        movieEntity.setTag(tag);
        movieEntity.setMovieId(movie.getId());
        movieEntity.setVoteCount(movie.getVoteCount());
        movieEntity.setVideo(movie.getVideo());
        movieEntity.setVoteAverage(movie.getVoteAverage());
        movieEntity.setPopularity(movie.getPopularity());
        movieEntity.setPosterPath(movie.getPosterPath());
        movieEntity.setOriginalLang(movie.getOriginalLanguage());
        movieEntity.setOriginalTitle(movie.getOriginalTitle());
        movieEntity.setBackdropPath(movie.getBackdropPath());
        movieEntity.setAdult(movie.getAdult());
        movieEntity.setOverview(movie.getOverview());
        movieEntity.setReleaseDate(movie.getReleaseDate());

        return movieEntity;
    }

    public static List<MovieTagEntity> getMovieTagEntityList(ShowListResult movieListResult, String tag){
        List<MovieTagEntity> movieTagEntities = new ArrayList<>();
        for(Movie i : movieListResult.getResults()){
            movieTagEntities.add(getMovieTagEntity(i.getId(), tag, i.getPage()));
        }
        return movieTagEntities;
    }

    public static MovieTagEntity getMovieTagEntity(int movieId, String tag, int page){
        MovieTagEntity movieTagEntity = new MovieTagEntity();
        movieTagEntity.setMovieId(movieId);
        movieTagEntity.setTag(tag);
        movieTagEntity.setPage(page);

        return movieTagEntity;
    }

}
