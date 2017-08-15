package com.itis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itis.R;
import com.itis.item.pic;
import com.itis.utils.API;

import java.util.List;

/**
 * 主页显示中间图片的适配器
 * Created by sks on 2016/3/24.
 */
public class item_Adapter extends BaseAdapter {

    private Context mContext;
    private List<pic> mList;
    private LayoutInflater inflater;

    public item_Adapter(Context context, List<pic> list) {
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(holder==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_iv_myrelease,null,false);
            holder.iv= (ImageView) convertView.findViewById(R.id.item_imageView);
            holder.IV= (ImageView) convertView.findViewById(R.id.Im_imageView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        pic p=mList.get(position);
        if(mList.size()==1){
            holder.IV.setVisibility(View.GONE);
            holder.iv.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(API.imiunew + p.getPic().trim()).into(holder.iv);
        }else{
            holder.IV.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.GONE);
            Glide.with(mContext).load(API.imiunew + p.getPic().trim()).into(holder.IV);
        }
   /*     convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBrower(position, mList);
            }
        });*/

        return convertView;
    }
    private class ViewHolder{
        ImageView iv,IV;
    }

/*    *//**
     * 打开图片查看器
     *//*
    protected void imageBrower(int position, List<pic> urls2) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (Serializable)urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        mContext.startActivity(intent);
    }*/
}
