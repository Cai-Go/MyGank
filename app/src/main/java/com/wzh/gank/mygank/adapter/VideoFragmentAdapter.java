package com.wzh.gank.mygank.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.model.VideoData;

import java.util.List;

/**
 *
 * @author WZH
 * @date 2017/9/7
 */

public class VideoFragmentAdapter extends BaseQuickAdapter<VideoData.ResultsBean, BaseViewHolder> {


    public VideoFragmentAdapter() {
        super(R.layout.vedio_fragment_item, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoData.ResultsBean item) {
        helper.setText(R.id.item_video_ragment_videoNameTextView, item.getDesc())
                .addOnClickListener(R.id.item_video_ragment_layout);
    }
}
