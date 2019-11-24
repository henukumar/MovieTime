package com.kalher.movietime.search;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.kalher.movietime.R;
import com.kalher.movietime.base.BaseHomeActivity;
import com.kalher.movietime.databinding.ActivitySearchBinding;

public class SearchActivity extends BaseHomeActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        content = binding.content.getRoot();
        mNavDrawer = binding.navDrawer;
        mToolbar = binding.content.mToolbar;
        nvHomeNavigation = binding.layoutNavigation.navigationView;
    }

}
