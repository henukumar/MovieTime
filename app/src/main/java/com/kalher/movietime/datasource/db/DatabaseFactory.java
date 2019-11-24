package com.kalher.movietime.datasource.db;

import com.kalher.movietime.base.MovieTime;

import androidx.room.Room;

public class DatabaseFactory {

    private static String MOVIE_DATABASE = "MovieTimeDb";

    private static MovietimeDatabase dbInstance;

    public static MovietimeDatabase getDbInstance(){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(MovieTime.getAppContext(), MovietimeDatabase.class, MOVIE_DATABASE).build();
        }
        return dbInstance;
    }

}
