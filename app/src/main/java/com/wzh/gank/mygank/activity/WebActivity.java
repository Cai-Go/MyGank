package com.wzh.gank.mygank.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.dao.FavoriteDAO;
import com.wzh.gank.mygank.model.Favorite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/9/8
 * @Description： 浏览器界面
 */

public class WebActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.web_avtivity_progressBar)
    ProgressBar mWebAvtivityProgressBar;

    @BindView(R.id.web_avtivity_webView)
    WebView mWebAvtivityWebView;

//    @BindView(R.id.share_imageView)
//    ImageView mShareImageView;

    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String TAG = "WebActivity";


    private String mId, mUrl, mTitle;
    private boolean isFavorite;


    private List<Favorite> mFavoriteList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    private void initData() {
        mId = getIntent().getStringExtra(EXTRA_ID);
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);


        //实例化对象
        FavoriteDAO favoriteDAO = new FavoriteDAO(WebActivity.this);
        favoriteDAO.openDB();

//        //根据 id 查找是否已经被收藏
//        mFavoriteList = new ArrayList<>();
//        if (mFavoriteList.size() == 0) {
//
//        } else {
//            mFavoriteList =favoriteDAO.queryOneData(mId);
//            if (mFavoriteList.get(0).getState().equals("1")) {
//                isFavorite = true;
//            } else {
//                isFavorite = false;
//            }
//        }


    }

    private void initView() {
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setElevation(8);

        WebSettings webSettings = mWebAvtivityWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        mWebAvtivityWebView.setWebChromeClient(new ChromeClient());
        mWebAvtivityWebView.setWebViewClient(new MyWebViewClient());


        mWebAvtivityWebView.loadUrl(mUrl);
        mToolbar.setTitle(mTitle);


    }

    private void initEvents() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        mShareImageView.setOnClickListener(this);
    }


    public static Intent newIntent(Context context, String id, String url, String title) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.favorite_imageView:
//                Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
//                if (isFavorite == false) {
//                    mFavoriteImageView.setSelected(true);
//                    //添加收藏
//
//                } else {
//                    mFavoriteImageView.setSelected(false);
//                    //取消收藏
//                }
//                break;
            case R.id.share_imageView:

                break;
        }
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mWebAvtivityProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mWebAvtivityProgressBar.setVisibility(View.GONE);
            } else {
                mWebAvtivityProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }

}
