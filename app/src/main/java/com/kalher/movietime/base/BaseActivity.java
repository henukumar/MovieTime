package com.kalher.movietime.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.appcompat.app.AppCompatActivity;

import com.kalher.movietime.R;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getName();
    protected ViewGroup mBaseContainer;

    /*
     * Lifecycle methods
     * */

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d(TAG, "onStart " + this.getClass().getSimpleName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieTime.setCurrentBaseActivity(this);
//        setContentView(R.layout.activity_base);   // we're never going to instantiate this activity
        Timber.d(TAG, "onCreate " + this.getClass().getSimpleName());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Timber.d(TAG, "onPostCreate " + this.getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d(TAG, "onResume " + this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d(TAG, "onPause " + this.getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d(TAG, "onStop " + this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d(TAG, "onDestroy " + this.getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {    // if activity starting after onStop
        super.onRestart();
        Timber.d(TAG, "onRestart " + this.getClass().getSimpleName());
    }

    /* Lifecycle methods finished*/

    public void setContentView(int layoutResId, boolean isOverride) {
        if (isOverride) { // inflate child activity layout inside the container(viewstub) in base activity to inherit all common features of baseactivity except common navdrawer
            setContentView(layoutResId);
        } else { // inflate child activity layout inside the container(viewstub) in basehomeactivity to add navigation drawer to each child activity
            View activityView = getLayoutInflater().inflate(layoutResId, null);
            mBaseContainer = activityView.findViewById(R.id.baseRootContainer);
            super.setContentView(layoutResId);
        }
    }

//    @Override
//    public void setContentView(int layoutResID) {
//        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mBaseContainer = (ViewGroup) inflater.inflate(R.layout.activity_base, null);
//        ViewStub view = mBaseContainer.findViewById(R.id.baseContainer);
//        view.setLayoutResource(layoutResID);
//        view.inflate();
//        super.setContentView(mBaseContainer);
//    }

    /*
     * add a menu.xml here, so that a functionality which is common from entire app can be managed from here
     * from every other activity only add menu dynamically
     * add menu.xml in any activity/fragment only if want to remove common option menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    protected abstract void init();
//    protected abstract void initView();
//    protected abstract void setUI();

}

/*
 * Contains all the code that should be common to all activities while
 * BaseHomeActivity contains code related to navigation drawer.
 * To inherit navigation drawer feature extend BaseActivity
 * */
