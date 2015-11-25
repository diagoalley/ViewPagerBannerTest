package com.diagoalley.android.viewpagerbannertest;

import android.app.Activity;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private View mBanner;
    private ViewPager banner;
    private LinearLayout ll_page;
    private PageIndicatorView piv;
    private ViewPagerAdapter vp_adapter;

    private List<Integer> list_banner = new ArrayList<Integer>();
    private List<ImageView> list_imageView = new ArrayList<ImageView>();
    private int[] imageSource = {R.drawable.ee,R.drawable.java,R.drawable.classic};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBannerView();
        initBannerData();
    }
    private void initView(){
        ll_page = (LinearLayout) findViewById(R.id.ll_page);
        banner = (ViewPager) findViewById(R.id.asvp_banner);

    }
    private void initBannerView(){
        piv = new PageIndicatorView(this);
        piv.setRectSize(MyUtils.getDisplayMetrics(this).widthPixels / 45,MyUtils.getDisplayMetrics(this).heightPixels /45,
                MyUtils.getDisplayMetrics(this).widthPixels /28);
        banner.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void initBannerData(){

        //list_imageView.clear();
        for(int source : imageSource){
            list_banner.add(source);
        }
        for (int i = 0; i < list_banner.size(); i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list_imageView.add(imageView);
        }
        ll_page.removeAllViews();
        if(list_banner.size() > 1){
            ll_page.addView(piv);
            piv.setTotalPage(list_banner.size());
            piv.setCurrentPage(0);
        }
        if (vp_adapter == null){
            vp_adapter = new ViewPagerAdapter(this, list_banner, list_imageView);
            banner.setAdapter(vp_adapter);
        } else {
            vp_adapter.updateList(list_banner,list_imageView);
            vp_adapter.notifyDataSetChanged();
        }
        banner.setCurrentItem(0);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            piv.setCurrentPage(position % list_banner.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
