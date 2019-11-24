package com.kalher.movietime.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.kalher.movietime.R;
import com.kalher.movietime.base.BaseActivity;
import com.kalher.movietime.common.view.CustomDrawerLayout;
import com.kalher.movietime.dashboard.DashboardActivity;
import com.kalher.movietime.search.SearchActivity;
import com.kalher.movietime.utility.ResourceUtility;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseHomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected CustomDrawerLayout mNavDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    protected Toolbar mToolbar;
    protected NavigationView nvHomeNavigation;
    protected View content;

//    protected NavDrawerItems      selectedItem;
    protected boolean             isShowTitle = true;
    protected String              currentMenuString = "MovieTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_home);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createDrawer();
    }

    private void createDrawer(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mNavDrawer, mToolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                if (isShowTitle) {
                    if (currentMenuString != null) {
                        setTitle(currentMenuString);
                    } else {
                        setTitle(" ");
                    }
                } else {
                    setTitle(" ");
                }
            }

            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float scaleFactor = 6f;
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
                content.setScaleX(1 - (slideOffset / scaleFactor));
                content.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };
        mNavDrawer.addDrawerListener(mDrawerToggle);
        mNavDrawer.setScrimColor(Color.TRANSPARENT);
        mNavDrawer.setDrawerElevation(0f);
        mDrawerToggle.syncState();  // https://stackoverflow.com/a/30275083/5176343
        mDrawerToggle.getDrawerArrowDrawable().setColor(ResourceUtility.getColor(R.color.colorOnPrimary));
        nvHomeNavigation.setNavigationItemSelectedListener(this);
        configureNavDrawerItems();

    }

    private void configureNavDrawerItems(){

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        switch (menuItem.getItemId()){
            case R.id.nav_movie:
                intent = new Intent(this, DashboardActivity.class);
                break;
            case R.id.nav_tv_series:
                break;
            case R.id.nav_celebrity:
                break;
            case R.id.nav_search:
                intent = new Intent(this, SearchActivity.class);
                break;
            case R.id.nav_setting:
                break;
        }
        if(intent != null){
            startActivity(intent);
            finish();
        }
        return false;
    }

}
