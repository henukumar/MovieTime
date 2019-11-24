package com.kalher.movietime.datasource.db;

import com.kalher.movietime.datasource.db.dao.MovieDao;
import com.kalher.movietime.datasource.db.dao.MovieTagDao;
import com.kalher.movietime.datasource.db.entity.MovieEntity;
import com.kalher.movietime.datasource.db.entity.MovieTagEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class, MovieTagEntity.class}, version = 1, exportSchema = false)
public abstract class MovietimeDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract MovieTagDao movieTagDao();

}
