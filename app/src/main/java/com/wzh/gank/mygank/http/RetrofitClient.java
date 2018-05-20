package com.wzh.gank.mygank.http;

import com.google.gson.GsonBuilder;
import com.wzh.gank.mygank.config.MyGankConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/9/5
 * @Description： 使用单例模式返回一个 GankRetrofit 对象
 */

public class RetrofitClient {


    private static GankRetrofit gankRetrofit;
    private static final Object monitor = new Object();
    private static Retrofit mRetrofit;


    private RetrofitClient() {

    }


    static {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        GsonConverterFactory mGsonConverterFactory = GsonConverterFactory
                .create(new GsonBuilder().create());
        mRetrofit = new Retrofit.Builder()
                .baseUrl(MyGankConfig.HOST)
                .client(mOkHttpClient)
                .addConverterFactory(mGsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static GankRetrofit getRetrofitInstance() {
        synchronized (monitor) {
            if (gankRetrofit == null) {
                gankRetrofit = mRetrofit.create(GankRetrofit.class);
            }
            return gankRetrofit;
        }
    }


}
