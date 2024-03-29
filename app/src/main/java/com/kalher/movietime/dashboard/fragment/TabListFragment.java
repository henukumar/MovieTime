package com.kalher.movietime.dashboard.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalher.movietime.R;
import com.kalher.movietime.dashboard.adapter.DashboardViewPagerAdapter;

public class TabListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabListFragment() {
    }

    public static TabListFragment newInstance(String param1, String param2) {
        TabListFragment fragment = new TabListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_list, container, false);
    }

    public void setUI(){
        setViewPager();
    }

    private void setViewPager(){
//        dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
//        binding.content.vpDashboard.setAdapter(dashboardViewPagerAdapter);
//        contentActivityDashboardBinding.vpDashboard.setOffscreenPageLimit(1);
//        binding.content.tlVpDashboard.setupWithViewPager(binding.content.vpDashboard);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
