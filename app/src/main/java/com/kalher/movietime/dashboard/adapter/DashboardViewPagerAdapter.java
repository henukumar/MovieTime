package com.kalher.movietime.dashboard.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kalher.movietime.common.AppConfig;
import com.kalher.movietime.common.view.DashboardTab;
import com.kalher.movietime.dashboard.fragment.ShowListFragment;

import java.util.List;

public class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {

    // Later get from app config
    private static final int pagesCount;
    private static List<DashboardTab> mDashboardTabList = null;

    static {
        mDashboardTabList = AppConfig.getDashboardTabs();
        pagesCount = mDashboardTabList.size();
    }

    public DashboardViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ShowListFragment.newInstance(mDashboardTabList.get(position).getUiAction());
    }

    @Override
    public int getCount() {
        return pagesCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDashboardTabList.get(position).getTitle();
//        return super.getPageTitle(position);
    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        String imageUrl = Media.getImageUrl(myObject.getObjectId(), images.get(position).getImageId());
//        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.object_details_image, collection, false);
//        ImageView pagerImage = layout.findViewById(R.id.pagerImage);
//        Media.setImageFromUrl(pagerImage, imageUrl);//call to GlideApp or Picasso to load the image into the ImageView
//        collection.addView(layout);
//        return layout;
//        return null;
//    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
//        container.removeView((View) view);
//    }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return super.isViewFromObject(view, object);
//        return view == object;
//    }
}

/*
* Generally Fragments are used in viewpager but we can use our custom layout also instead of fragments
* To do that we have to override instantiateItem & destroyItem
* https://medium.com/@cdmunoz/the-easiest-way-to-work-with-viewpager-and-without-fragments-c62ec3e8b9f3
* */
