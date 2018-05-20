package com.wzh.gank.mygank.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wzh.gank.mygank.R;

/**
 * @author 开发者：WZH
 * @date 创建时间：2018/5/20
 * @Description： Glide 加载类
 */
public class GlideUtils {

    protected static final RequestOptions mRequestOptions = new RequestOptions()
            .placeholder(R.drawable.background);

    public static void loadImageWithPlaceHolder(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(mRequestOptions)
                .into(imageView);
    }
}
