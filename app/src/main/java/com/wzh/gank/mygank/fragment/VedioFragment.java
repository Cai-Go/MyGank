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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.activity.WebActivity;
import com.wzh.gank.mygank.adapter.VideoFragmentAdapter;
import com.wzh.gank.mygank.base.LazyLoadBaseFragment;
import com.wzh.gank.mygank.http.RetrofitClient;
import com.wzh.gank.mygank.model.VideoData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.POST;

/**
 * @author WZH
 * @date 2017/9/5
 */

public class VedioFragment extends LazyLoadBaseFragment
        implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.video_fragment_recyclerview)
    RecyclerView mVideoFragmentRecyclerview;
    @BindView(R.id.video_fragment_swipeRefreshLayout)
    SwipeRefreshLayout mVideoFragmentSwipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private static final String TAG = "VedioFragment";

    private int page = 1;
    private int pageSize = 10;

    private String title;

    private String mId;
    private String mUrl;
    private String mTitle;

    private List<VideoData.ResultsBean> mList;


    private static VedioFragment mVedioFragment;

    private LinearLayoutManager mLinearLayoutManager;
    private VideoFragmentAdapter mVideoFragmentAdapter;

    public static Fragment newInstance(String info) {
        Bundle args = new Bundle();
        mVedioFragment = new VedioFragment();
        args.putString("info", info);
        Log.d(TAG, info);
        mVedioFragment.setArguments(args);
        return mVedioFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = mVedioFragment.getArguments().getString("info");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.video_fragment;
    }

    @Override
    protected void initView(View rootView) {
        initView();
        initEvents();
    }


    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        mVideoFragmentSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initView() {

        mList = new ArrayList<>();

        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mVideoFragmentRecyclerview.setLayoutManager(mLinearLayoutManager);
        mVideoFragmentAdapter = new VideoFragmentAdapter(mList);
        mVideoFragmentRecyclerview.setAdapter(mVideoFragmentAdapter);

        mVideoFragmentSwipeRefreshLayout.setOnRefreshListener(this);
        mVideoFragmentSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mVideoFragmentAdapter.setOnLoadMoreListener(this, mVideoFragmentRecyclerview);


    }

    private void initEvents() {
        mVideoFragmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VideoData.ResultsBean vedio = (VideoData.ResultsBean) adapter.getData().get(position);

                mId = vedio.get_id();
                mUrl = vedio.getUrl();
                mTitle = vedio.getDesc();
                Intent intent = WebActivity.newIntent(getActivity(), mId, mUrl, mTitle);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("CheckResult")
    private void loadVideo() {
        Log.d(TAG, Integer.toString(page));
        RetrofitClient.getRetrofitInstance()
                .getVideoData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoData>() {
                    @Override
                    public void accept(VideoData videoData) {
                        doData(videoData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.d(TAG, "throwable" + throwable);
                    }
                });
    }

    private void doData(VideoData videoData) {
        if (null != mVideoFragmentSwipeRefreshLayout && mVideoFragmentSwipeRefreshLayout.isRefreshing()) {
            mVideoFragmentSwipeRefreshLayout.setRefreshing(false);
            mVideoFragmentAdapter.setEnableLoadMore(true);
        }

        if (null != mVideoFragmentAdapter && mVideoFragmentAdapter.isLoading()) {
            mVideoFragmentSwipeRefreshLayout.setEnabled(true);
        }
        if (page == 1) {
            mList.clear();
            mList.addAll(videoData.getResults());
        } else {
            mList.addAll(videoData.getResults());
        }
        mVideoFragmentAdapter.notifyDataSetChanged();

        if (videoData.getResults().size() == pageSize) {
            mVideoFragmentAdapter.loadMoreComplete();
        } else if (videoData.getResults().size() < pageSize) {
            mVideoFragmentAdapter.loadMoreEnd();
        }

    }


    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        //停止加载更多
        mVideoFragmentAdapter.setEnableLoadMore(false);
        page = 1;
        loadVideo();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        page++;
        loadVideo();
        mVideoFragmentSwipeRefreshLayout.setEnabled(false);
    }
}
