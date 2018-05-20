package com.wzh.gank.mygank.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.model.MeiziData;
import com.wzh.gank.mygank.utils.GlideUtils;

/**
 *
 * @author WZH
 * @date 2017/9/6
 */

public class MeiziFragmentAdapter extends BaseQuickAdapter<MeiziData.ResultsBean, BaseViewHolder> {

    public MeiziFragmentAdapter() {
        super(R.layout.meiziz_fragment_item, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, MeiziData.ResultsBean item) {
        Log.d("MeiziFragmentAdapter", item.getUrl());
        ImageView imageView = helper.getView(R.id.item_meizi_fragment_imageView);
//        RequestOptions options = new RequestOptions()
////                .placeholder(R.drawable.background);
////        Glide.with(mContext)
////                .load(item.getUrl())
////                .apply(options)
////                .into(imageView);

        GlideUtils.loadImageWithPlaceHolder(mContext, item.getUrl(), imageView);

    }
}
