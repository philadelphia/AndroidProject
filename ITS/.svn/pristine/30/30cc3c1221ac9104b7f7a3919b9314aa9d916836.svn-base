package com.itis.fragments;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itis.R;
import com.itis.activitys.itemActivity;
import com.itis.adapter.HomeAdapter;
import com.itis.item.Appconfig;
import com.itis.item.Root;
import com.itis.item.homeData;
import com.itis.listview.Pullablerecyclerview;
import com.itis.utils.Connector;
import com.itis.utils.PullToRefreshLayout;
import com.itis.utils.ScreenUtils;
import com.itis.utils.Tools;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邵光
 * 推荐fragment，app进来的页面
 */
public class HomeFragment extends Fragment implements PullToRefreshLayout.OnRefreshListener{
    @Bind(R.id.head_view)
    RelativeLayout headView;
    @Bind(R.id.top_btn)
    Button topBtn;
    @Bind(R.id.listView)
    Pullablerecyclerview listView;
    @Bind(R.id.refresh_view)
    PullToRefreshLayout refreshView;
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置
    View view;
    private List<homeData> date;
    private List<homeData> List;
    private HomeAdapter adapter;
    final int pag=1;
    final int pageA=1;
    final int pageB=2;
//    Handler handler=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initData(0);
        initView();
        updateView();
    return view;
     }
    /**
     * 获取数据
     */
    private void initData( int p) {
        Tools.showProgressDialog(getActivity(), getResources().getString(R.string.loadingData));
        Connector.world_cricle_list(String.valueOf(p), "3", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e("sssss",result);
                    Gson gson=new Gson();
                    Root<ArrayList<homeData>> data = gson.fromJson(result, new TypeToken<Root<ArrayList<homeData>>>() {
                    }.getType());
                    date=data.getData();
                    if (pag == 1) {
                        Message msg = handler.obtainMessage(pageA,date);
                        handler.sendMessage(msg);
                    } else {
                        Message msg = handler.obtainMessage(pageB,date);
                        handler.sendMessage(msg);
                    }
                    Tools.closeProgressDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("ssssssssss", "ssssssss");
                Tools.closeProgressDialog();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("ssssssssss", "sddddddddd");
                Tools.closeProgressDialog();
            }
            @Override
            public void onFinished() {
                Log.e("ssssssssss", "vvvvvvvvvv");
                Tools.closeProgressDialog();
            }
        });
    }

    /**
     * 分页加载数据
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case pageA:
                    List=(List<homeData>)msg.obj;
                    adapter = new HomeAdapter(getActivity(),List);
                    listView.setAdapter(adapter);
                    break;
                case pageB:
                    List<homeData> getMore =(List<homeData>)msg.obj;
                    List.addAll(getMore);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private void initView() {
                        refreshView.setOnRefreshListener(this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                        scrollFlag = false;
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view
                                .getCount() - 1)) {
                            topBtn.setVisibility(View.VISIBLE);
                        }
                        // 判断滚动到顶部
                        else if (view.getFirstVisiblePosition() == 0) {
                            topBtn.setVisibility(View.GONE);
                        } else {
                            topBtn.setVisibility(View.VISIBLE);
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                        scrollFlag = false;
                        break;
                }
            }
            /**
             * firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
             * visibleItemCount：当前能看见的列表项个数（小半个也算） totalItemCount：列表项共数
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // 当开始滑动且ListView底部的Y轴点超出屏幕最大范围时，显示或隐藏顶部按钮
                if (scrollFlag
                        && ScreenUtils.getScreenViewBottomHeight(view) >= ScreenUtils
                        .getScreenHeight(getActivity())) {
                    if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
                        topBtn.setVisibility(View.GONE);
                    } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
                        topBtn.setVisibility(View.VISIBLE);
                    } else {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }
        });
        }
      private void updateView() {
          refreshView.setOnRefreshListener(this);
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent intent = new Intent();
                  intent.setClass(getActivity(), itemActivity.class);
                  Appconfig.setHomeDatas(List);
                  intent.putExtra("position", position+"");
                  getActivity().startActivity(intent);
              }
          });
    }
    @OnClick({R.id.top_btn})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.top_btn:
                setListViewPos(0);
                topBtn.setVisibility(View.GONE);
                break;
        }
    }
    /**
     * 滚动ListView到指定位置
     *
     * @param pos
     */
    private void setListViewPos(int pos) {
        if (Build.VERSION.SDK_INT >= 8) {
            listView.smoothScrollToPosition(pos);
        } else {
            listView.setSelection(pos);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ButterKnife.bind(this, view);
    }
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        // 下拉刷新操作
        topBtn.setVisibility(View.GONE);
        handler.postDelayed(new Runnable() {
            public void run() {
                date.clear();
                initData(0);
                refreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }, 1500);
    }
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        handler.postDelayed(new Runnable() {
            public void run() {
              /*  int s=p+20;
                initData(s);*/
                refreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }, 1500);
    }
}
