package com.kalher.movietime.dashboard;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.kalher.movietime.R;
import com.kalher.movietime.base.BaseHomeActivity;
import com.kalher.movietime.dashboard.adapter.DashboardViewPagerAdapter;
import com.kalher.movietime.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseHomeActivity {

    private ActivityDashboardBinding binding;
    private DashboardViewPagerAdapter dashboardViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard, true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        content = binding.content.getRoot();
        mNavDrawer = binding.navDrawer;
        mToolbar = binding.content.mToolbar;
        nvHomeNavigation = binding.layoutNavigation.navigationView;

        init();
//        initView();
        setUI();
    }

    private void init(){

    }

    private void setUI(){
        setViewPager();
    }

    private void setViewPager(){
        dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        binding.content.vpDashboard.setAdapter(dashboardViewPagerAdapter);
//        contentActivityDashboardBinding.vpDashboard.setOffscreenPageLimit(1);
        binding.content.tlVpDashboard.setupWithViewPager(binding.content.vpDashboard);
    }

}
