package com.itis.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sks on 2016/4/27.
 * 王盼
 */
public abstract class AllBaseAdapter<T> extends BaseAdapter {
    private List<T> datas;
    private Context mContext;
    private int resId;

    public AllBaseAdapter(List<T> datas, Context mContext, int resId) {
        this.datas = datas;
        this.mContext = mContext;
        this.resId = resId;
    }
    @Override
    public int getCount() {
        return datas == null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
     return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = BaseViewHolder.getHolder(convertView, mContext, resId);
        fillData(baseViewHolder, position);
        return baseViewHolder.getmConvertView();
    }
    public abstract void fillData(BaseViewHolder myViewHolder, int postion);
}
