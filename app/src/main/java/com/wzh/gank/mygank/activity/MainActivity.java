package com.wzh.gank.mygank.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.adapter.MainViewpagerAdapter;
import com.wzh.gank.mygank.fragment.HomeFragment;
import com.wzh.gank.mygank.fragment.MeiZiFragment;
import com.wzh.gank.mygank.fragment.ToolsFragment;
import com.wzh.gank.mygank.fragment.VedioFragment;
import com.wzh.gank.mygank.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WZH
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
    @BindView(R.id.main_bnv)
    BottomNavigationView mMainBnv;
    private MainViewpagerAdapter mMainViewpagerAdapter;
    private MenuItem mMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initEvents();
    }


    private void initView() {
        mMainViewpagerAdapter = new MainViewpagerAdapter(getSupportFragmentManager());
        mMainViewpagerAdapter.addFragments(HomeFragment.newInstance("主页"));
        mMainViewpagerAdapter.addFragments(MeiZiFragment.newInstance("妹子"));
        mMainViewpagerAdapter.addFragments(VedioFragment.newInstance("视频"));
        mMainViewpagerAdapter.addFragments(ToolsFragment.newInstance("工具"));
        mMainViewpager.setOffscreenPageLimit(4);
        mMainViewpager.setAdapter(mMainViewpagerAdapter);
        BottomNavigationViewHelper.disableShiftMode(mMainBnv);
    }

    private void initEvents() {
        mMainBnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_home_menu:
                                mMainViewpager.setCurrentItem(0);
                                break;
                            case R.id.nav_meizi_menu:
                                mMainViewpager.setCurrentItem(1);
                                break;
                            case R.id.nav_video_menu:
                                mMainViewpager.setCurrentItem(2);
                                break;
                            case R.id.nav_tools_menu:
                                mMainViewpager.setCurrentItem(3);
                                break;

//                            case R.id.nav_me_menu:
//                                mMainViewpager.setCurrentItem(3);
//                                break;
                        }

                        return true;
                    }
                });


        mMainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    mMainBnv.getMenu().getItem(position).setChecked(false);
                }
                mMainBnv.getMenu().getItem(position).setChecked(true);
                mMenuItem = mMainBnv.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
