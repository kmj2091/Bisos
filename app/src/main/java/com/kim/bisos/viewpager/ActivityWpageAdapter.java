package com.kim.bisos.viewpager;

/**
 * Created by kim on 2016-08-24.
 */
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

public class ActivityWpageAdapter extends PagerAdapter {

    private ArrayList<View> pageViews;
    private Context mContext;


    public ActivityWpageAdapter(ArrayList<View> pageViews, Context mContext) {
        super();
        this.pageViews = pageViews;
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(View v, int position) {



        ((ViewPager) v).addView(pageViews.get(position));
        return pageViews.get(position);
    }

    @Override
    public void destroyItem(View v, int position, Object arg2) {
        ((ViewPager) v).removeView(pageViews.get(position));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public int getCount() {
        return pageViews.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object arg1) {
        return v == arg1;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}