package com.kalher.movietime.utility;

import android.util.Log;

import com.kalher.movietime.BuildConfig;

public class Logger {

    private static final boolean isForceEnable = false;
    private static final boolean isEnabled;

    static {
        isEnabled = isForceEnable || BuildConfig.DEBUG ? true : false;
    }

    public static void v(String TAG, String Message){
        if(isEnabled){
            Log.v(TAG, Message);
        }
    }

    public static void d(String TAG, String Message){
        if(isEnabled){
            Log.d(TAG, Message);
        }
    }

    public static void i(String TAG, String Message){
        if(isEnabled){
            Log.i(TAG, Message);
        }
    }

    public static void w(String TAG, String Message){
        if(isEnabled){
            Log.w(TAG, Message);
        }
    }

    public static void e(String TAG, String Message){
        if(isEnabled){
            Log.e(TAG, Message);
        }
    }

}
