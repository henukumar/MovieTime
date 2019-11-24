package com.kalher.movietime.datasource.db.dao;

import com.kalher.movietime.datasource.db.entity.MovieEntity;
import com.kalher.movietime.datasource.model.movie.Movie;
import com.kalher.movietime.datasource.pageloader.MovieDataSource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(MovieEntity... movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieList(List<MovieEntity> movieList);

    @Query("SELECT * FROM movie")
    List<MovieEntity> getAllMovies();

    @Query("SELECT * FROM movie WHERE page_number = :page AND tag = :tag")
    List<MovieEntity> getMovieByPage(int page, String tag);

    @Query("SELECT * FROM movie WHERE movie_id = :id")
    MovieEntity getMovieById(int id);

    @Update
    void updateMovie(MovieEntity movie);

    @Delete
    void deleteMovie(MovieEntity movie);

    @Query("DELETE FROM movie")
    void deleteAllMovie();

}
