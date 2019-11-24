package com.kalher.movietime.utility;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

import com.kalher.movietime.base.MovieTime;

public class ResourceUtility {

    public static float getDimens(int dimResId) {
        return MovieTime.getContext().getResources().getDimension(dimResId);
    }

    public static float getFloat(int dimResId) {
        TypedValue typedValue = new TypedValue();
        MovieTime.getContext().getResources().getValue(dimResId, typedValue, true);
        return typedValue.getFloat();
    }

    public static boolean getBool(int boolResId) {
        return MovieTime.getContext().getResources().getBoolean(boolResId);
    }

    public static int getInt(int intResId) {
        return MovieTime.getContext().getResources().getInteger(intResId);
    }

    public static String getString(int strResId) {
        return MovieTime.getContext().getResources().getString(strResId);
    }

    public static String getString(int strResId, Object... options) {
        return MovieTime.getContext().getResources().getString(strResId, options);
    }

    public static String getQuantityString(int strResId, int quantity) {
        return MovieTime.getContext().getResources().getQuantityString(strResId, quantity);
    }

    public static String getPluralQuantityString(int strResId, int quantity, int count) {
        return MovieTime.getContext().getResources().getQuantityString(strResId, quantity, count);
    }

    public static String[] getStringArray(int strArrId) {
        return MovieTime.getContext().getResources().getStringArray(strArrId);
    }

    public static Drawable getDrawable(int id) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return MovieTime.getContext().getResources().getDrawable(id, MovieTime.getContext().getTheme());
        }else {
            return null;
        }
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = MovieTime.getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = MovieTime.getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px /(displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static Drawable resize(Drawable image, int height, int width) {
        Bitmap b =((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, width, height, false);
        return new BitmapDrawable(MovieTime.getContext().getResources(), bitmapResized);
    }

    public static int getColor(int colorResId) {
        return ContextCompat.getColor(MovieTime.getContext(), colorResId);
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            return MovieTime.getContext().getResources().getColor(colorResId, MovieTime.getContext().getTheme());
//        } else {
//            return MovieTime.getContext().getResources().getColor(colorResId);
//        }
    }

    public static String getStringByAttr(Context context, int strResId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(strResId, typedValue, true);
        TypedArray arr =
                context.obtainStyledAttributes(typedValue.data, new int[]{
                        strResId});
        String primaryColor = arr.getString(0);
        return primaryColor;
    }

    public static int getColorByAttr(Context context, int colorResId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(colorResId, typedValue, true);
        TypedArray arr =
                context.obtainStyledAttributes(typedValue.data, new int[]{
                        colorResId});
        int primaryColor = arr.getColor(0, -1);
        return primaryColor;
    }

    public static Drawable getDrawableByAttr(Context context, int drawableId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(drawableId, typedValue, true);
        TypedArray arr =
                context.obtainStyledAttributes(typedValue.data, new int[]{
                        drawableId});
        Drawable drawable = arr.getDrawable(0);
        return drawable;
    }

    public static int getDrawableIdByAttr(Context context, int drawableId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(drawableId, typedValue, true);
        TypedArray arr =
                context.obtainStyledAttributes(typedValue.data, new int[]{
                        drawableId});
        return arr.getResourceId(0, 0);
    }

    public static int[] getIntArray(int intArrId) {
        return MovieTime.getContext().getResources().getIntArray(intArrId);
    }

    public static int getDrawableByName(String iconName) {
        return MovieTime.getContext().getResources().getIdentifier(iconName, "drawable",
                MovieTime.getRootPackageName());
    }

    public static String getStringByName(String stringName) {
        return getString(MovieTime.getContext().getResources().getIdentifier(stringName, "string",
                MovieTime.getRootPackageName()));
    }

    public static int getCountryFlag(String countryCode) {
        if(StringUtility.isNonEmpty(countryCode)) {
            return getDrawableByName("country_"+countryCode.toLowerCase());
        }
        return 0;
    }
    
}
