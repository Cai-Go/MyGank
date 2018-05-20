package com.wzh.gank.mygank.http;

import com.wzh.gank.mygank.config.MyGankConfig;
import com.wzh.gank.mygank.model.HomeData;
import com.wzh.gank.mygank.model.MeiziData;
import com.wzh.gank.mygank.model.VideoData;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WZH on 2017/9/5.
 */

public interface GankRetrofit {
    //获取主页数据

    /**
     * http://gank.io/api/data/类型/数据量/页数
     *
     * @param type 类型
     * @param page 页数
     * @return
     */
    @GET("data/{type}/" + MyGankConfig.GANK_SIZE + "/{page}")
    Observable<HomeData> getHomeData(@Path("type") String type,
                                     @Path("page") int page);


    /**
     * 获取妹纸数据
     * http://gank.io/api/data/福利/请求的数据量/页数
     *
     * @param page 页数
     * @return
     */

    @GET("data/" + MyGankConfig.MEIZI + "/" + MyGankConfig.MEIZI_SIZE + "/{page}")
    Observable<MeiziData> getMeiziData(@Path("page") int page);


    /**
     * 获取视频数据
     * http://gank.io/api/data/休息视频/请求的数据量/页数
     *
     * @param page
     * @return
     */
    @GET("data/" + MyGankConfig.Video + "/" + MyGankConfig.Video_SIZE + "/{page}")
    Observable<VideoData> getVideoData(@Path("page") int page);

}

