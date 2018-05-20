package com.wzh.gank.mygank.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.model.HomeData;

import java.util.List;

/**
 * Created by WZH on 2017/9/6.
 */

public class GankFragmentAdapter extends BaseQuickAdapter<HomeData.ResultsBean, BaseViewHolder> {


    public GankFragmentAdapter() {
        super(R.layout.gank_fragment_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.ResultsBean item) {
        Log.d("GankFragmentAdapter",item.getUrl());
        helper.setText(R.id.item_decs_textView, item.getDesc())
                .setText(R.id.item_anthor_textView, item.getWho())
                .setText(R.id.item_date_textView,
                        item.getCreatedAt().substring(0,item.getCreatedAt().indexOf("T")));

    }
}
