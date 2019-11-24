package com.kalher.movietime.common;

import com.kalher.movietime.common.view.DashboardTab;

import java.util.ArrayList;
import java.util.List;

public class AppConfig {

    public static final int EXECUTOR_THREAD_COUNT = 10;

    public static class MovieConfig {
        public static final int ITEM_IN_ONE_PAGE = 20;
        public static final int INITIAL_LOAD_SIZE_HINT = 20;
        public static final int PREFETCH_DISTANCE = 10;
        public static final boolean SHOW_PLACEHOLDER = false;
    }

    public static class TVConfig {

    }

    public static List<DashboardTab> getDashboardTabs(){
        List<DashboardTab> dashboardTabList = new ArrayList<>();

        DashboardTab dashboardTab = new DashboardTab();
        dashboardTab.setTitle("Now Playing");
        dashboardTab.setUiAction(UIAction.GET_NOW_PLAYING_MOVIES);
        dashboardTabList.add(dashboardTab);

        dashboardTab = new DashboardTab();
        dashboardTab.setTitle("Upcoming");
        dashboardTab.setUiAction(UIAction.GET_UPCOMING_MOVIES);
        dashboardTabList.add(dashboardTab);

        dashboardTab = new DashboardTab();
        dashboardTab.setTitle("Top Rated");
        dashboardTab.setUiAction(UIAction.GET_TOP_RATED_MOVIES);
        dashboardTabList.add(dashboardTab);

        dashboardTab = new DashboardTab();
        dashboardTab.setTitle("Popular");
        dashboardTab.setUiAction(UIAction.GET_POPULAR_MOVIES);
        dashboardTabList.add(dashboardTab);

        return dashboardTabList;
    }

}
