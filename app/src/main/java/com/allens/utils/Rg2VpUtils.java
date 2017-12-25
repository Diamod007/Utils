package com.allens.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;


/**
 * Created by Allens on 2016/9/8.
 */
public class Rg2VpUtils {

    private List<Fragment> mFragmentList;
    private FragmentManager mManager;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private List<View> mViewList;
    private Context mContext;

    private Rg2VpUtils(Context context) {
        this.mContext = context;
    }

    private static Rg2VpUtils mInstance;

    public static Rg2VpUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (Rg2VpUtils.class) {
                if (mInstance == null) {
                    mInstance = new Rg2VpUtils(context);
                }
            }
        }
        return mInstance;
    }

    /***
     *
     *
     *
     * @param radioGroup
     * @param viewPager
     * @param viewList   view 集合
     */
    public void tabControl(RadioGroup radioGroup, ViewPager viewPager, List<View> viewList) {
        this.mViewList = viewList;
        this.mRadioGroup = radioGroup;
        this.mViewPager = viewPager;
        mViewPager.setAdapter(new MyPagerAdapter());
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        initClickListener();
    }

    /***
     * 选项卡   viewpager 效果
     *
     * @param radioGroup
     * @param viewPager
     * @param manager
     * @param fragmentList 碎片集合
     */
    public void tabControl(RadioGroup radioGroup, ViewPager viewPager, FragmentManager manager, List<Fragment> fragmentList) {
        this.mFragmentList = fragmentList;
        this.mRadioGroup = radioGroup;
        this.mManager = manager;
        this.mViewPager = viewPager;
        mViewPager.setAdapter(new MyFragmentPagerAdapter(mManager));
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        initClickListener();
    }


    private void initClickListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页数在移动过程中调用的方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //页数发生改变的时候调用该地方
            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) mRadioGroup.getChildAt(position);
                rb.setChecked(true);
            }

            //页数改变时候滚动状态的监听
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                int i = group.indexOfChild(rb);
                mViewPager.setCurrentItem(i);
            }
        });
    }

    /**
     * 作用：Adapter
     * name: Allens
     * created at 2016/9/20 12:29
     */
    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = mViewList.get(position);
            container.removeView(view);
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

}
