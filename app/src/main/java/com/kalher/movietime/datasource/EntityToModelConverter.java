package com.kalher.movietime.datasource;

import com.kalher.movietime.datasource.db.entity.MovieEntity;
import com.kalher.movietime.datasource.model.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class EntityToModelConverter {

    public static Movie getMovieFromMovieEntity(MovieEntity entity){
        Movie movie = new Movie();

        movie.setId(entity.getMovieId());
        movie.setVoteCount(entity.getVoteCount());
//        movie.setVideo(entity.getVideo());
        movie.setVoteAverage(entity.getVoteAverage());
        movie.setPopularity(entity.getPopularity());
        movie.setPosterPath(entity.getPosterPath());
//        movie.setOriginalLanguage(entity.getOriginalLanguage());
        movie.setOriginalTitle(entity.getOriginalTitle());
        movie.setBackdropPath(entity.getBackdropPath());
//        movie.setAdult(entity.getAdult());
        movie.setOverview(entity.getOverview());
        movie.setReleaseDate(entity.getReleaseDate());

        return movie;
    }

    public static List<Movie> getMovieListFromMovieEntityList(List<MovieEntity> entityList){
        List<Movie> movieList = new ArrayList<>();
        for(MovieEntity entity : entityList){
            movieList.add(getMovieFromMovieEntity(entity));
        }
        return movieList;
    }

}
