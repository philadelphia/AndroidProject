package com.itis.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 邵光
 * 首页容器适配器
 * Created by sks on 2016/4/27.
 */
public class MainTabPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mList;

    public MainTabPageAdapter(FragmentManager fm,Context context,List<Fragment> list) {
        super(fm);
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
