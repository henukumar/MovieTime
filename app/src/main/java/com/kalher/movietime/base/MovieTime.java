package com.kalher.movietime.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.kalher.movietime.BuildConfig;
import com.kalher.movietime.common.AppConfig;

import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import timber.log.Timber;

public class MovieTime extends Application {

    private static final String     TAG                    = MovieTime.class.getName();

    /*
    * The downside is that there is no guarantee that the non-static onCreate() will have been
    * called before some static initialization code tries to fetch your Context object
    * (will work perfectly with non-static code), https://stackoverflow.com/a/5114361/5176343
    */
    private static Context          sApplicationContext   = null;
    private static BaseActivity     sCurrentBaseActivity  = null;

    private static Executor sExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Creating Application Context");
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites()
//                    .detectNetwork().penaltyLog().build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
//                    .detectLeakedClosableObjects().penaltyLog().build());
//        }
        sApplicationContext = getApplicationContext();
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(Travolution.this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return sCurrentBaseActivity != null ? sCurrentBaseActivity : sApplicationContext;
    }

    public static Context getAppContext(){
        return sApplicationContext;
    }

    public static BaseActivity getCurrentBaseActivity() {
        return sCurrentBaseActivity;
    }

    public static String getRootPackageName() {
        return getContext() != null ? getContext().getPackageName() : "";
    }

    public static void setCurrentBaseActivity(BaseActivity currentBaseActivity) {
        MovieTime.sCurrentBaseActivity = currentBaseActivity;
    }

    public boolean isUIThread(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        e.printStackTrace();
        if(isUIThread()) {
//            restartApp();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
//                    restartApp();
                }
            });
        }
    }

    public static Executor getCommonExecutor(){
        if(sExecutor == null){
            sExecutor = Executors.newFixedThreadPool(AppConfig.EXECUTOR_THREAD_COUNT);
        }
        return sExecutor;
    }

}
