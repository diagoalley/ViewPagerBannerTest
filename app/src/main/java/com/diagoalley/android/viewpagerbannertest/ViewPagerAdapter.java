package com.diagoalley.android.viewpagerbannertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/11/24.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Activity context;
    private List<Integer> list_image;
    private List<ImageView> pageViews;

    public ViewPagerAdapter(Activity context, List<Integer> list_image, List<ImageView> pageViews){
        this.context = context;
        this.list_image = new ArrayList<>();
        this.list_image.addAll(list_image);
        this.pageViews = new ArrayList<>();
        this.pageViews.addAll(pageViews);
    }

    public void updateList(List<Integer> list_image, List<ImageView> pageViews){
        this.list_image.clear();
        this.pageViews.clear();
        this.list_image.addAll(list_image);
        this.pageViews.addAll(pageViews);
    }

    @Override
    public int getCount() {
        return list_image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (pageViews.get(position % pageViews.size()).getParent() != null) {
            ((ViewPager) pageViews.get(position % pageViews.size())
                    .getParent()).removeView(pageViews.get(position
                    % pageViews.size()));
        }
        pageViews.get(position % pageViews.size()).setImageResource(list_image.get(position % list_image.size()));
        container.addView(pageViews.get(position % pageViews.size()));
        return pageViews.get(position % pageViews.size());
    }

}

