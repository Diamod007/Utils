package com.allens.utils;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */

public class Tab2VpUtils {

    private Tab2VpUtils() {

    }

    private static Tab2VpUtils newInstance;

    public static Tab2VpUtils getInstance() {
        if (newInstance == null) {
            synchronized (Tab2VpUtils.class) {
                if (newInstance == null) {
                    newInstance = new Tab2VpUtils();
                }
            }
        }
        return newInstance;
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private String[] arr;

    /***
     *
     * @param tabLayout
     * @param viewPager
     * @param fragmentList
     * @param fragmentManager
     * @param arr 数组
     */
    public void tabControl(TabLayout tabLayout, ViewPager viewPager, List<Fragment> fragmentList, FragmentManager fragmentManager, String[] arr) {
        this.arr = arr;
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        initSet();
    }

    private void initSet() {
        viewPager.setAdapter(new MyViewPagerFragmentAdapter(fragmentManager));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < arr.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(arr[i]);
//            tab.setIcon(R.drawable.buttom_category_seletor);//图片选择器，只能在上面？？
        }
    }

    private class MyViewPagerFragmentAdapter extends FragmentPagerAdapter {

        public MyViewPagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

}
