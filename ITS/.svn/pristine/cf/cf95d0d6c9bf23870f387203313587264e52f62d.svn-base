package com.itis.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itis.R;
import com.itis.activitys.PopDialogActivity;
import com.itis.item.Appconfig;
import com.itis.item.comm;
import com.itis.item.homeData;
import com.itis.utils.API;
import com.itis.utils.Connector;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;
import com.itis.view.GridViewScrollView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 邵光
 * <p/>
 * 推荐适配器
 * Created by sks on 2016/4/28.
 */
public class HomeAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<homeData> mItemList;
    private Context context;
    private HashMap<Integer, String> changM;
    private HashMap<Integer, String> changY;
    private PopupWindow popupwindow;

    public HomeAdapter(Context context, List<homeData> messagedata) {
        this.context = context;
        this.mItemList = messagedata;
        changM = new HashMap<Integer, String>();
        changY = new HashMap<Integer, String>();
        for (int i = 0; i < mItemList.size(); i++) {
            changM.put(i, mItemList.get(i).getM_num());
            changY.put(i, mItemList.get(i).getY_num());
        }
    }

    /**
     * 获取Item总数
     */
    @Override
    public int getCount() {
        if (mItemList != null) {
            return mItemList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mItemList != null) {
            return mItemList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context.getApplicationContext(), R.layout.item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final homeData mTitleArray = mItemList.get(position);
        holder.tvGuanzhu.setVisibility(View.GONE);
        holder.ibXiaoxi.setVisibility(View.VISIBLE);
        holder.tvItemName.setText(mTitleArray.getNickname());
        holder.tvContent.setText(mTitleArray.getContent());
        holder.tvfanyi.setText(mTitleArray.getFanyi());
        Glide.with(context).load(API.imiunew + mTitleArray.getUser_pic()).into(holder.firstFragmentIv);
        Glide.with(context).load(API.coutry + mTitleArray.getCountry_pic()).into(holder.ivcontry);
        holder.tvtime.setText(ToastUtils.showTime(mTitleArray.getCtime()));
        int i = Integer.parseInt(mTitleArray.getM_num()) + Integer.parseInt(mTitleArray.getY_num());
        float m = (float) Integer.parseInt(mTitleArray.getM_num()) / (float) i;
        float y = (float) Integer.parseInt(mTitleArray.getY_num()) / (float) i;
        holder.meiyisi.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, m));
        holder.youyisi.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, y));
        holder.tvmeiisi.setText(changM.get(position));
        holder.tvyouyisi.setText(changY.get(position));
        item_Adapter adapter = new item_Adapter(context, mTitleArray.getPic());
        holder.ListView.setAdapter(adapter);
        final int r = mTitleArray.getPic().size();
        if (r == 1) {
            holder.ListView.setNumColumns(1);
        } else {
            holder.ListView.setNumColumns(3);
        }
        /**
         * 没意思
         */
        holder.rlout_meiyisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.showProgressDialog(context, context.getResources().getString(R.string.loadingData));
                Connector.meaning(mTitleArray.getId(), "2", new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getString("code").equals("1")) {
                                int no = Integer.parseInt(holder.tvmeiisi.getText().toString()) + 1;
                                holder.tvmeiisi.setText(no + "");
                                changM.put(position, no + "");
                                ToastUtils.showToast(context, jsonObject.getString("message"));
                            } else {
                                ToastUtils.showToast(context, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Tools.closeProgressDialog();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Tools.closeProgressDialog();
                    }

                    @Override
                    public void onFinished() {
                        Tools.closeProgressDialog();
                    }
                });
            }
        });
        /**
         * 有意思
         */
        holder.rlout_yousiyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.showProgressDialog(context, context.getResources().getString(R.string.loadingData));
                Connector.meaning(mTitleArray.getId(), "1", new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getString("code").equals("1")) {
                                int yes = Integer.parseInt(holder.tvyouyisi.getText().toString()) + 1;
                                holder.tvyouyisi.setText(yes + "");
                                changY.put(position, yes + "");
                                ToastUtils.showToast(context, jsonObject.getString("message"));
                            } else {
                                ToastUtils.showToast(context, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Tools.closeProgressDialog();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Tools.closeProgressDialog();
                    }

                    @Override
                    public void onFinished() {
                        Tools.closeProgressDialog();
                    }
                });
            }
        });
        holder.ibXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm comms=new comm();
                comms.setSid(mTitleArray.getUid());
                comms.setWid(mTitleArray.getId());
                Appconfig.setComms(comms);
                Log.e("ssss",mTitleArray.getUid()+mTitleArray.getId());
                context.startActivity(new Intent(context, PopDialogActivity.class));
            }
        });
        return convertView;

    }
    static class ViewHolder {
        @Bind(R.id.firstFragment_iv)
        CircleImageView firstFragmentIv;
        @Bind(R.id.tv_item_name)
        TextView tvItemName;
        @Bind(R.id.ib_xiaoxi)
        RelativeLayout ibXiaoxi;
        @Bind(R.id.tv_guanzhu)
        TextView tvGuanzhu;
        @Bind(R.id.rl_out)
        RelativeLayout rlOut;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.ListView)
        GridViewScrollView ListView;
        @Bind(R.id.iv_contry)
        ImageView ivcontry;
        @Bind(R.id.tv_fanyi)
        TextView tvfanyi;
        @Bind(R.id.tv_time)
        TextView tvtime;
        @Bind(R.id.lout_meiyisi)
        LinearLayout meiyisi;
        @Bind(R.id.lout_youyisi)
        LinearLayout youyisi;
        @Bind(R.id.tv_youyisi)
        TextView tvyouyisi;
        @Bind(R.id.tv_meiyisi)
        TextView tvmeiisi;
        @Bind(R.id.rlout_meiyisi)
        RelativeLayout rlout_meiyisi;
        @Bind(R.id.rlout_yousiyi)
        RelativeLayout rlout_yousiyi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}