package com.kalher.movietime.datasource.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//@Entity(tableName = "movie_tag",
//        indices = {@Index(value = {"movie_id", "tag"}, unique = true)},
//        primaryKeys = {"movie_id", "tag"},
//        foreignKeys = {@ForeignKey(entity = MovieEntity.class, parentColumns = "movie_id", childColumns = "movie_id")})    //for now, without indexing declaring a column to have unique value is not supported in room
@Entity(tableName = "movie_tag")
public class MovieTagEntity implements IEntity{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "page")
    private int page;

    @NonNull
    @ColumnInfo(name = "tag")
    private String tag;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
