package com.wzh.gank.mygank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzh.gank.mygank.R;
import com.wzh.gank.mygank.base.LazyLoadBaseFragment;

/**
 *
 * @author WZH
 * @date 2017/9/18
 */

public class ToolsFragment extends LazyLoadBaseFragment {

    public static Fragment newInstance(String info) {
        Bundle args = new Bundle();
        ToolsFragment toolsFragment = new ToolsFragment();
        args.putString("info", info);
        toolsFragment.setArguments(args);
        return toolsFragment;
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.tools_fragment;
    }

    @Override
    protected void initView(View rootView) {

    }
}
