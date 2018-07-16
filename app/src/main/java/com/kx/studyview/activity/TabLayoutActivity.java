package com.kx.studyview.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kx.studyview.BaseActivity;
import com.kx.studyview.R;

import java.lang.reflect.Field;

import butterknife.BindView;

public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private String[] week = {"周一", "周二", "周三", "周四", "周五"};
    private String[] day = {"1", "2", "3", "4", "5"};

    @Override
    protected void initData() {
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter();
        viewPager.setAdapter(tabPagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout, 20, 20);
            }
        });
        for (int i = 0; i < tabPagerAdapter.getCount(); i++) {
            TabLayout.Tab tabAt = tablayout.getTabAt(i);
            if (tabAt!=null){
                View tabView = getTabView(i);
                final int finalI = i;
                tabView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(finalI);
                    }
                });
                tabAt.setCustomView(tabView);
            }
            if (i==0){
                tabAt.getCustomView().findViewById(R.id.txt_week).setSelected(true);
                tabAt.getCustomView().findViewById(R.id.txt_day).setSelected(true);
                tabAt.getCustomView().findViewById(R.id.iv).setVisibility(View.VISIBLE);
            }else {
                tabAt.getCustomView().findViewById(R.id.iv).setVisibility(View.GONE);
            }
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.txt_week).setSelected(true);
                tab.getCustomView().findViewById(R.id.txt_day).setSelected(true);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.txt_week).setSelected(false);
                tab.getCustomView().findViewById(R.id.txt_day).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public int getRootLayoutIds() {
        return R.layout.activity_tab_layout;
    }

    class TabPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(TabLayoutActivity.this);
            textView.setText("Tab" + position);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView tv_week =  view.findViewById(R.id.txt_week);
        tv_week.setText(week[position]);
        TextView tv_day =  view.findViewById(R.id.txt_day);
        tv_day.setText(day[position]);
        ImageView img = (ImageView) view.findViewById(R.id.iv);
       // img.setImageResource(imageResId[position]);
        return view;
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
