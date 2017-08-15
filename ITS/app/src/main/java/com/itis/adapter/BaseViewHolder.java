package com.itis.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sks on 2016/4/27.
 * 王盼
 */
public class BaseViewHolder {
    private View mConvertView;
    private SparseArray<View> sparseArray = new SparseArray<>();
    public View getmConvertView() {
        return mConvertView;
    }

    public BaseViewHolder(Context context,int resId) {
        mConvertView = LayoutInflater.from(context).inflate(resId, null);
        mConvertView.setTag(this);
    }


    public static BaseViewHolder getHolder(View convertView,Context context,int resId) {
        BaseViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new BaseViewHolder(context,resId);
        }else {
            myViewHolder = (BaseViewHolder) convertView.getTag();
        }
        return myViewHolder;
    }

    public View findViewById(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

    public void setText(int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }
}
