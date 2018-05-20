package com.wzh.gank.mygank.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wzh.gank.mygank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/9/10
 * @Description： 欢迎页
 */


public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.splash_activity_imageView)
    ImageView mSplashActivityImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mSplashActivityImageView,
                "scaleX",
                1f, 2f, 1f);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}
