package com.wzh.gank.mygank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.adapter.HomeFragmentViewpagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author WZH
 * @date 2017/9/5
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.home_fragment_tablayout)
    TabLayout mHomeFragmentTablayout;
    @BindView(R.id.home_fragment_viewpager)
    ViewPager mHomeFragmentViewpager;
    Unbinder unbinder;


    private HomeFragmentViewpagerAdapter mHomeFragmentViewpagerAdapter;


    public static Fragment newInstance(String info) {
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        args.putString("info", info);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initEvents();
        return view;
    }




    private void initView() {
        mHomeFragmentViewpagerAdapter = new HomeFragmentViewpagerAdapter(getActivity().getSupportFragmentManager());
        mHomeFragmentViewpager.setAdapter(mHomeFragmentViewpagerAdapter);
        mHomeFragmentViewpager.setOffscreenPageLimit(6);
        mHomeFragmentTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mHomeFragmentTablayout.setupWithViewPager(mHomeFragmentViewpager);
    }

    private void initEvents() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
