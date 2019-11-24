package com.kalher.movietime.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kalher.movietime.datasource.db.entity.MovieTagEntity;

import java.util.List;

@Dao
public interface MovieTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(MovieTagEntity record);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecords(MovieTagEntity... records);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecordList(List<MovieTagEntity> recordsList);

    @Query("SELECT * FROM movie_tag")
    List<MovieTagEntity> getAllMovies();

    @Query("SELECT * FROM movie_tag WHERE tag = :tag AND page = :page")
    MovieTagEntity getMoviesByTagAndPage(String tag, int page);

    @Query("DELETE FROM movie_tag")
    void deleteAllMovie();

}
