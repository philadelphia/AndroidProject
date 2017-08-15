package com.itis.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itis.R;
import com.itis.fragments.HomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends AppCompatActivity {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.dongtai_iv)
    ImageView dongtaiIv;
    @Bind(R.id.dongtai_tv)
    TextView dongtaiTv;
    @Bind(R.id.nums)
    TextView nums;
    @Bind(R.id.liuyan_iv)
    ImageView liuyanIv;
    @Bind(R.id.liuyan_tv)
    TextView liuyanTv;
    @Bind(R.id.liuyan)
    RelativeLayout liuyan;
    @Bind(R.id.gfnew_iv)
    ImageView gfnewIv;
    @Bind(R.id.gfnews_tv)
    TextView gfnewsTv;
    @Bind(R.id.list_news)
    ListView listNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.back, R.id.dongtai_iv, R.id.dongtai_tv, R.id.nums, R.id.liuyan_iv, R.id.liuyan_tv, R.id.gfnew_iv, R.id.gfnews_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.dongtai_iv:
            case R.id.dongtai_tv:
                Intent DTIntent = new Intent(NewsActivity.this,DongTaiActivity.class);
                startActivity(DTIntent);
                break;
            case R.id.nums:
                break;
            case R.id.liuyan_iv:
            case R.id.liuyan_tv:
                Intent LYIntent = new Intent(NewsActivity.this,LeaveMessageActivity.class);
                startActivity(LYIntent);
                break;
            case R.id.gfnew_iv:
            case R.id.gfnews_tv:
                Intent GFIntent = new Intent(NewsActivity.this, HomeFragment.class);
                startActivity(GFIntent);
                break;
        }
    }
}
