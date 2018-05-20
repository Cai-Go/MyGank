package com.wzh.gank.mygank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.wzh.gank.mygank.fragment.GankFragment;

/**
 * Created by WZH on 2017/9/5.
 */

public class HomeFragmentViewpagerAdapter extends FragmentStatePagerAdapter {


    String[] title = {"Android", "iOS", "前端", "瞎推荐", "拓展资源", "App"};

    public HomeFragmentViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GankFragment.newInstance(title[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }




    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
