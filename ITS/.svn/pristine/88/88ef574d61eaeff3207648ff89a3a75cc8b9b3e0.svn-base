package com.itis.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itis.R;
import com.itis.adapter.item_Adapter;
import com.itis.item.Appconfig;
import com.itis.item.homeData;
import com.itis.utils.API;
import com.itis.utils.Connector;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;
import com.itis.view.GridViewScrollView;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sks on 2016/5/5.
 * 详情
 */
public class itemActivity extends Activity {
    List<homeData> urls;
    int position;
    @Bind(R.id.firstFragment_iv)
    CircleImageView firstFragmentIv;
    @Bind(R.id.tv_item_name)
    TextView tvItemName;
    @Bind(R.id.iv_contry)
    ImageView ivContry;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.ib_xiaoxi)
    RelativeLayout ibXiaoxi;
    @Bind(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @Bind(R.id.rl_out)
    RelativeLayout rlOut;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_fanyi)
    TextView tvFanyi;
    @Bind(R.id.gv_image)
    GridViewScrollView gvImage;
    @Bind(R.id.lo_out)
    LinearLayout loOut;
    @Bind(R.id.item_listview)
    ListView itemListview;
    @Bind(R.id.tv_country)
    TextView tvCountry;
    @Bind(R.id.tv_pinglun)
    TextView tvPinglun;
    @Bind(R.id.tv_shoucang)
    TextView tvShoucang;
    @Bind(R.id.rb_youyisi)
    RadioButton rbYouyisi;
    @Bind(R.id.rb_meiyisi)
    RadioButton rbMeiyisi;
    @Bind(R.id.rb_pinglun)
    RadioButton rbPinglun;
    @Bind(R.id.rb_shoucang)
    RadioButton rbShoucang;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        urls = Appconfig.getHomeDatas();
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        uid=urls.get(position).getUid();
        initView();
    }
    private void initView() {
        tvGuanzhu.setVisibility(View.VISIBLE);
        tvGuanzhu.setText(R.string.guan_zhu);
        ibXiaoxi.setVisibility(View.GONE);
        tvItemName.setText(urls.get(position).getNickname());
        Glide.with(this).load(API.meaning + urls.get(position).getUser_pic()).error(R.drawable.user_error).into(firstFragmentIv);
        Glide.with(this).load(API.coutry + urls.get(position).getCountry_pic()).into(ivContry);
        tvTime.setText(ToastUtils.showTime(urls.get(position).getCtime()));
        tvContent.setText(urls.get(position).getContent());
        tvFanyi.setText(urls.get(position).getFanyi());
        tvCountry.setText(getResources().getString(R.string.yiyou) + urls.get(position).getCountry_partake() + getResources().getString(R.string.coumtry_canyu));
        tvPinglun.setText(getResources().getString(R.string.pinglun) + urls.get(position).getComment_count());
        tvShoucang.setText(getResources().getString(R.string.slide_collect) + urls.get(position).getCollection_count());
        rbYouyisi.setText(getResources().getString(R.string.youyisi)+urls.get(position).getY_num());
        rbMeiyisi.setText(getResources().getString(R.string.meiyisi)+urls.get(position).getM_num());
        rbPinglun.setText(getResources().getString(R.string.pinglun)+urls.get(position).getComment_count());
        rbShoucang.setText(getResources().getString(R.string.slide_collect) + urls.get(position).getCollection_count());
        item_Adapter adapter = new item_Adapter(this, urls.get(position).getPic());
        gvImage.setAdapter(adapter);
        final int r = urls.get(position).getPic().size();
        if (r == 1) {
            gvImage.setNumColumns(1);
        } else {
            gvImage.setNumColumns(3);
        }
    }
    @OnClick({R.id.tv_guanzhu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guanzhu:
                if (tvGuanzhu.getTag().equals("off")) {
                    getBtGz();
                    tvGuanzhu.setTag("on");
                    tvGuanzhu.setText(R.string.guan_zhu); //加关注
                } else {
                    getGZ();
                    tvGuanzhu.setTag("off");
                    tvGuanzhu.setText(R.string.yi_guan_zhu); //取消关注
                }
                break;
        }
    }
    /**
     * 取消关注
     */
    private void getGZ() {
        Tools.showProgressDialog(itemActivity.this, getResources().getString(R.string.loadingData));
        Connector.followOff(uid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
             try {
                 JSONObject jsonObject = new JSONObject(result);
                 if (jsonObject.getString("code").equals("1")){
                     ToastUtils.showToast(itemActivity.this, jsonObject.getString("message"));

                 }
             }catch (JSONException e){
                 e.printStackTrace();
             }
                Log.e("quxiaoguanzhu",result);
                Tools.closeProgressDialog();
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

    /**
     * 加关注
     */
    private void getBtGz() {
        Tools.showProgressDialog(itemActivity.this, getResources().getString(R.string.loadingData));
        Connector.follow(uid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("guanzhu", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1")){
                        ToastUtils.showToast(itemActivity.this, jsonObject.getString("message"));

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Tools.closeProgressDialog();
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
}
