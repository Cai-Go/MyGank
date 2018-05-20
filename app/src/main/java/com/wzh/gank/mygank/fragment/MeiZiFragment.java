package com.wzh.gank.mygank.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.activity.PhotoViewActivity;
import com.wzh.gank.mygank.adapter.MeiziFragmentAdapter;
import com.wzh.gank.mygank.base.LazyLoadBaseFragment;
import com.wzh.gank.mygank.http.RetrofitClient;
import com.wzh.gank.mygank.model.MeiziData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WZH
 * @date 2017/9/5
 */

public class MeiZiFragment extends LazyLoadBaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.meizi_fragment_recyclerview)
    RecyclerView mMeiziFragmentRecyclerview;
    @BindView(R.id.meizi_fragment_swiprefreshlayout)
    SwipeRefreshLayout mMeiziFragmentSwiprefreshlayout;
//    Unbinder unbinder;


    private static final String TAG = "MeiZiFragment";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int page = 1;
    private int pageSize = 10;

    private boolean isRefresh;

    private String title;

    private static MeiZiFragment meiZiFragment;

    private List<MeiziData.ResultsBean> mList;
    private LinearLayoutManager mLinearLayoutManager;
    private MeiziFragmentAdapter mMeiziFragmentAdapter;

    public static Fragment newInstance(String info) {
        Bundle args = new Bundle();
        meiZiFragment = new MeiZiFragment();
        args.putString("info", info);
        meiZiFragment.setArguments(args);
        return meiZiFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = meiZiFragment.getArguments().getString("info");
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.meizi_fragment;
    }

    @Override
    protected void initView(View rootView) {
        initView();
        initEvents();
    }


    private void initView() {
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(Color.WHITE);
        mList = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mMeiziFragmentRecyclerview.setLayoutManager(mLinearLayoutManager);
        mMeiziFragmentAdapter = new MeiziFragmentAdapter();
        //打开加载动画
//        mMeiziFragmentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mMeiziFragmentRecyclerview.setAdapter(mMeiziFragmentAdapter);

        mMeiziFragmentSwiprefreshlayout.setOnRefreshListener(this);
        mMeiziFragmentSwiprefreshlayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mMeiziFragmentAdapter.setOnLoadMoreListener(this, mMeiziFragmentRecyclerview);
    }

    private void initEvents() {
        mMeiziFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url = mList.get(position).getUrl();
                Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        mMeiziFragmentSwiprefreshlayout.setRefreshing(true);
        refresh();
    }

    @SuppressLint("CheckResult")
    private void loadMeizi() {
        Log.d(TAG, Integer.toString(page));
        RetrofitClient.getRetrofitInstance()
                .getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeiziData>() {
                    @Override
                    public void accept(MeiziData meiziData) {
                        addList(meiziData);
                        setData(isRefresh, meiziData.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.d(TAG, "throwable" + throwable);
                    }
                });
    }

    private void setData(boolean isRefresh, List<MeiziData.ResultsBean> results) {
        page++;
        final int size = results == null ? 0 : results.size();
        if (isRefresh) {
            mMeiziFragmentAdapter.setNewData(results);
        } else {
            if (size > 0) {
                mMeiziFragmentAdapter.addData(results);
            }
        }

        if (size < pageSize) {
            mMeiziFragmentAdapter.loadMoreEnd(isRefresh);
        } else {
            mMeiziFragmentAdapter.loadMoreComplete();
        }


    }

    private void addList(MeiziData meiziData) {
        if (meiziData.getResults() != null) {
            mList.addAll(meiziData.getResults());
        }
    }




    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        page = 1;
        mMeiziFragmentAdapter.setEnableLoadMore(false);
        isRefresh = true;
        loadMeizi();
        mMeiziFragmentAdapter.setEnableLoadMore(true);
        mMeiziFragmentSwiprefreshlayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        isRefresh = false;
        loadMeizi();
    }
}
