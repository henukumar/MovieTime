package com.kalher.movietime.utility.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.kalher.movietime.base.MovieTime;

public class SharedPrefFactory {

    private static SharedPreferences appSharedPreferences;

    public static SharedPreferences getAppSPInstance(){
        if(appSharedPreferences == null){
            appSharedPreferences = MovieTime.getAppContext().getSharedPreferences(SharedPrefKey.MOVIETIME_SHAREDPREF, Context.MODE_PRIVATE);
        }
        return appSharedPreferences;
    }

    public static SharedPreferences getSharedPreference(String spName){
        return MovieTime.getAppContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getActivitySharedPreference(){
        return MovieTime.getCurrentBaseActivity().getPreferences(Context.MODE_PRIVATE);
    }

}
