package com.wzh.gank.mygank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.wzh.gank.mygank.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/10/12
 * @Description： 大图
 */

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.photoView)
    PhotoView mPhotoView;
    @BindView(R.id.close_photo_view)
    ImageView mClosePhotoView;


    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoview_activity);
        ButterKnife.bind(this);

        initData();
        initView();

        initEvents();
    }

    private void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

    }

    private void initView() {


        Glide.with(this).load(url).into(mPhotoView);
    }

    private void initEvents() {

    }

    @OnClick(R.id.close_photo_view)
    public void onViewClicked() {
        finish();
    }
}
