package com.itis.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itis.R;
import com.itis.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by sks on 2016/5/3.
 */
public class ReleaseAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mList;
    private LayoutInflater inflater;
    private boolean mDeleteFlag;

    public ReleaseAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.mDeleteFlag = deleteFlag;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size() ;
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPathList(ArrayList<String> pathList) {
        this.mList = pathList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == holder) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_release_gridview, null, false);
            holder.iv = (ImageView) convertView.findViewById(R.id.releaseGV_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        if (position < mList.size()) {
//            ImageLoader.getInstance().display(getItem(position), holder.iv, 90, 90);
//        } else {
//            holder.iv.setBackgroundResource(R.mipmap.image_bg);    // 最后一个显示加号图片
//        }
     ImageLoader.getInstance().display(getItem(position), holder.iv, 90, 90);
        Log.i("tag", "getView: "+getItem(position));
        return convertView;
    }

    private class ViewHolder {
        ImageView iv;
    }
}
