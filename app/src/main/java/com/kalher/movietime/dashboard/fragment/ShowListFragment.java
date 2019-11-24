package com.kalher.movietime.dashboard.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalher.movietime.R;
import com.kalher.movietime.base.BaseFragment;
import com.kalher.movietime.base.BaseViewHolder;
import com.kalher.movietime.base.ShowItem;
import com.kalher.movietime.common.UIAction;
import com.kalher.movietime.common.constant.LayoutType;
import com.kalher.movietime.common.interfaces.IShow;
import com.kalher.movietime.dashboard.DashboardViewModel;
import com.kalher.movietime.dashboard.adapter.ItemListAdapter;
import com.kalher.movietime.databinding.FragmentItemListBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ShowListFragment extends BaseFragment {
    protected String TAG = this.getClass().getSimpleName();

    private OnFragmentInteractionListener mListener;

    private FragmentItemListBinding binding;
    private ItemListAdapter mItemListAdapter;
    private BaseViewHolder.ItemViewClickListener mItemSelectedListener;

    private DashboardViewModel dashboardViewModel;
    private List<IShow> mItemList = new ArrayList<>();

    private Context mContext;

    private static final String CONST_UIACTION = "ui_action";

    private UIAction mUIAction;

    public ShowListFragment() {
    }

    public static ShowListFragment newInstance(UIAction uiAction) {
        ShowListFragment fragment = new ShowListFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONST_UIACTION, uiAction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            mUIAction = (UIAction) getArguments().getSerializable(CONST_UIACTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_item_list, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        setUI();
    }

    private void init() {
//        contentActivityDashboardBinding = ContentActivityDashboardBinding.inflate(getLayoutInflater());
        setAdapter();
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        final Observer<PagedList<IShow>> itemListObserver = showList -> {
//            Toast.makeText(getContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
//            mItemList.clear();
//            mItemList.addAll(showList);
//            setOrNotifyAdapter(showList);
//            mItemListAdapter.submitList(null);
            mItemListAdapter.submitList(showList);
        };

        Timber.i(TAG, "getItemListLiveData for UIAction:-" + mUIAction);
//        dashboardViewModel.getItemListLiveData(mUIAction).observe(this, itemListObserver); // in fragment don't pass this as when it gets detached this passed instance will not get removed & a new one will get added when fragment is attached next time leading to memory leak
        dashboardViewModel.getItemListLiveData(mUIAction).observe(getViewLifecycleOwner(), itemListObserver);   // instead use viewLifeCycleOwner
    }

    private void setUI() {
//        Toast.makeText(getContext(), "SetUI", Toast.LENGTH_SHORT).show();
        setMoviesRV();
    }

    private void setMoviesRV() {
        mItemSelectedListener = view -> {

        };
        binding.rvItemList.setLayoutManager(getDashboardRVLayoutManager());
//        contentActivityDashboardBinding.rvDashboardMovie.setLayoutManager(getDashboardRVLayoutManager());
//        setOrNotifyAdapter();
    }

    private void setAdapter() {
        if (mItemListAdapter == null) {
            mItemListAdapter = new ItemListAdapter(mItemSelectedListener, ShowItem.DIFF_CALLBACK);
            binding.rvItemList.setLayoutManager(getDashboardRVLayoutManager());
            binding.rvItemList.setAdapter(mItemListAdapter);
        }
//        mItemListAdapter = new ItemListAdapter(mItemList, mItemSelectedListener);
//        mItemListAdapter.submitList(pagedList);
//        mItemListAdapter.notifyDataSetChanged();
    }

    private RecyclerView.LayoutManager getDashboardRVLayoutManager() {
        int layoutType = getSelectedLayoutType();

        switch (layoutType) {
            case LayoutType.TYPE_LAYOUT_LIST:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                return linearLayoutManager;

            case LayoutType.TYPE_LAYOUT_GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                return gridLayoutManager;
        }

        return null;
    }

    private int getSelectedLayoutType() {
        return LayoutType.TYPE_LAYOUT_LIST;
//        return LayoutType.TYPE_LAYOUT_GRID;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
