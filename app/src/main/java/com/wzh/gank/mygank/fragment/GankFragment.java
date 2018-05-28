package com.wzh.gank.mygank.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.activity.WebActivity;
import com.wzh.gank.mygank.adapter.GankFragmentAdapter;
import com.wzh.gank.mygank.base.LazyLoadBaseFragment;
import com.wzh.gank.mygank.http.RetrofitClient;
import com.wzh.gank.mygank.model.HomeData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author WZH
 * @date 2017/9/5
 */

public class GankFragment extends LazyLoadBaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "GankFragment";
    private static final String TYPE = "type";


    @BindView(R.id.gank_fragment_recyclerView)
    RecyclerView mGankFragmentRecyclerView;
    @BindView(R.id.gank_fragment_swiprefreshLayout)
    SwipeRefreshLayout mGankFragmentSwiprefreshLayout;

    private String type;
    private int page = 1;
    private int pageSize = 20;

    private String id;
    private String url;
    private String title;


    private List<HomeData.ResultsBean> mList;

    private LinearLayoutManager mLinearLayoutManager;
    private GankFragmentAdapter mGankFragmentAdapter;


    public static Fragment newInstance(String type) {
        GankFragment gankFragment = new GankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        gankFragment.setArguments(bundle);
        return gankFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.gank_fragment;
    }

    @Override
    protected void initView(View rootView) {
        initView();
        initEvents();
    }


    private void initView() {

        mList = new ArrayList<>();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mGankFragmentRecyclerView.setLayoutManager(mLinearLayoutManager);
        mGankFragmentAdapter = new GankFragmentAdapter(mList);


        mGankFragmentRecyclerView.setAdapter(mGankFragmentAdapter);
        //调用刷新
        mGankFragmentSwiprefreshLayout.setOnRefreshListener(this);
        mGankFragmentSwiprefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        //调用加载更多
        mGankFragmentAdapter.setOnLoadMoreListener(this, mGankFragmentRecyclerView);


    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        mGankFragmentSwiprefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initEvents() {
        mGankFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeData.ResultsBean resultsBean = (HomeData.ResultsBean) adapter.getData().get(position);

                id = resultsBean.get_id();
                url = resultsBean.getUrl();
                title = resultsBean.getDesc();
                Intent intent = WebActivity.newIntent(getActivity(), id, url, title);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("CheckResult")
    private void loadGank() {
        Log.d(TAG, "页数" + page);
        RetrofitClient.getRetrofitInstance()
                .getHomeData(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeData>() {
                    @Override
                    public void accept(HomeData homeData) {
                        doData(homeData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.d(TAG, "throwable" + throwable);
                    }
                });

    }

    private void doData(HomeData homeData) {

        if (null != mGankFragmentSwiprefreshLayout && mGankFragmentSwiprefreshLayout.isRefreshing()) {
            mGankFragmentSwiprefreshLayout.setRefreshing(false);
            mGankFragmentAdapter.setEnableLoadMore(true);
        }

        if (null != mGankFragmentAdapter && mGankFragmentAdapter.isLoading()) {
            mGankFragmentSwiprefreshLayout.setEnabled(true);
        }


        if (page == 1) {
            mList.clear();
            mList.addAll(homeData.getResults());
        } else {
            mList.addAll(homeData.getResults());
        }
        mGankFragmentAdapter.notifyDataSetChanged();


        if (homeData.getResults().size() == pageSize) {
            mGankFragmentAdapter.loadMoreComplete();
        } else if (homeData.getResults().size() < pageSize) {
            mGankFragmentAdapter.loadMoreEnd();
        }

    }





    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        //下次请求的页数
        page = 1;
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mGankFragmentAdapter.setEnableLoadMore(false);
        loadGank();

    }

    @Override
    public void onLoadMoreRequested() {
        //加载更多
        loadMore();
    }

    private void loadMore() {
        page++;
        loadGank();
        mGankFragmentSwiprefreshLayout.setEnabled(false);
    }

}
