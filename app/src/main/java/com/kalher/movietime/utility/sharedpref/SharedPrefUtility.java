package com.kalher.movietime.utility.sharedpref;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class SharedPrefUtility {
    /* To read from sharedpreference
    * */
    public static boolean getBooleanWithDefFalse(String key){
        return getBoolean(key, false);
    }

    public static boolean getBooleanWithDefTrue(String key){
        return getBoolean(key, true);
    }

    public static boolean getBoolean(String key, boolean defValue){
        return SharedPrefFactory.getAppSPInstance().getBoolean(key, defValue);
    }

    public static float getFloat(String key){
        return getFloat(key, 0);
    }

    public static float getFloat(String key, float defValue){
        return SharedPrefFactory.getAppSPInstance().getFloat(key, defValue);
    }

    public static int getInt(String key){
        return getInt(key, 0);
    }

    public static int getInt(String key, int defValue){
        return SharedPrefFactory.getAppSPInstance().getInt(key, defValue);
    }

    public static long getLong(String key){
        return getInt(key, 0);
    }

    public static long getLong(String key, long defValue){
        return SharedPrefFactory.getAppSPInstance().getLong(key, defValue);
    }

    public static String getString(String key){
        return getString(key, "");
    }

    public static String getString(String key, String defValue){
        return SharedPrefFactory.getAppSPInstance().getString(key, defValue);
    }

    public static Set<String> getStringSet(String key){
        return SharedPrefFactory.getAppSPInstance().getStringSet(key, new HashSet<String>());
    }

    /* These methods add data to application level sharedpreference
     * */
    public static void addLong(String key, long value){
        SharedPreferences sharedPreferences = SharedPrefFactory.getAppSPInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void addInt(String key, int value){
        SharedPreferences sharedPreferences = SharedPrefFactory.getAppSPInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void addString(String key, String value){
        SharedPreferences sharedPreferences = SharedPrefFactory.getAppSPInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void addFloat(String key, float value){
        SharedPreferences sharedPreferences = SharedPrefFactory.getAppSPInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void addStringSet(String key, HashSet<String> value){
        SharedPreferences sharedPreferences = SharedPrefFactory.getAppSPInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

}

/*
 * https://stackoverflow.com/a/23705667/5176343
 *  it is safe to store the SharedPreferences instance as a static reference. According to the javadocs it is a singleton, so its source from getSharedPreferences is already a static reference.
 *  It is not safe to store the SharedPreferences.Editor because it is possible two threads may be manipulating the same editor object at the same time. Granted, the damage this would cause is relatively minor if you happen to have already been doing it. Instead, get an instance of an editor in each editing method.
 * */

