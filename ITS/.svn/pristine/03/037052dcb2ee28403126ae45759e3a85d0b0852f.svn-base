package com.itis.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itis.R;
import com.itis.adapter.MainTabPageAdapter;
import com.itis.fragments.AtttentionFragment;
import com.itis.fragments.HomeFragment;
import com.itis.fragments.OuYuFragment;
import com.itis.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 邵光
 * 页面的父容器
 */
public class MainTabActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.mainTab_viewPager)
    ViewPager mainTabViewPager;
    @Bind(R.id.mainTab_textView)
    TextView mainTabTextView;
    @Bind(R.id.mainTab_llCurrentIndex)
    LinearLayout mainTabLlCurrentIndex;
    @Bind(R.id.mainTab_drawerLayout)
    DrawerLayout mainTabDrawerLayout;
    @Bind(R.id.mainTab_leftMenu)
    LinearLayout mainTabLeftMenu;
    @Bind(R.id.mainTab_iv)
    ImageView mainTabIv;
    @Bind(R.id.mainTab_ll_message)
    LinearLayout mainTabLlMessage;
    @Bind(R.id.mainTab_ll_release)
    LinearLayout mainTabLlRelease;
    @Bind(R.id.mainTab_ivOne)
    ImageView mainTabIvOne;
    @Bind(R.id.mainTab_ivTwo)
    ImageView mainTabIvTwo;
    @Bind(R.id.mainTab_ivThree)
    ImageView mainTabIvThree;
    @Bind(R.id.mainTab_rlTiele)
    RelativeLayout mainTabRlTiele;
    @Bind(R.id.mainTab_leftClose)
    LinearLayout mainTabLeftClose;
    @Bind(R.id.photo)
    CircleImageView photo;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.search)
    ImageView search;
    @Bind(R.id.search_tv)
    TextView searchTv;
    @Bind(R.id.friend)
    ImageView friend;
    @Bind(R.id.friend_tv)
    TextView friendTv;
    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.collect_tv)
    TextView collectTv;
    @Bind(R.id.world)
    ImageView world;
    @Bind(R.id.world_tv)
    TextView worldTv;
    @Bind(R.id.mean)
    ImageView mean;
    @Bind(R.id.mean_tv)
    TextView meanTv;
    @Bind(R.id.footprint)
    ImageView footprint;
    @Bind(R.id.footprint_tv)
    TextView footprintTv;
    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.setting)
    ImageView setting;
    @Bind(R.id.setting_tv)
    TextView settingTv;
    @Bind(R.id.translate)
    ImageView translate;
    @Bind(R.id.translate_tv)
    TextView translateTv;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.line)
    TextView line;
    @Bind(R.id.release)
    ImageView release;
    @Bind(R.id.news)
    ImageView news;
    private Context context = null;
    private ImageView[] ivButtons = null;
    private int fragmentindex;
    private static boolean isExit = false;
    private static final String TAG = "MainTabActivity";
    private PopupWindow popupwindow;
    private long exitTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        context = MainTabActivity.this;
        ButterKnife.bind(this);
        initPopupwindow();
        setupView();
        initView();
        resetTextView();
    }

    /**
     * viewoager的监听事件
     */
    private void initView() {
        mainTabViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                fragmentindex = position;
                resetTextView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * Fragment的初始化
     */
    private void setupView() {
        HomeFragment home = new HomeFragment();
        AtttentionFragment home_guanzhu = new AtttentionFragment();
        OuYuFragment home_ouyu = new OuYuFragment();
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(home);
        list.add(home_guanzhu);
        list.add(home_ouyu);
        MainTabPageAdapter adapter = new MainTabPageAdapter(getSupportFragmentManager(), context, list);
        mainTabViewPager.setAdapter(adapter);
        ivButtons = new ImageView[3];
        ivButtons[0] = (ImageView) findViewById(R.id.mainTab_ivOne);
        ivButtons[1] = (ImageView) findViewById(R.id.mainTab_ivTwo);
        ivButtons[2] = (ImageView) findViewById(R.id.mainTab_ivThree);
    }
    /**
     * 控件的显示
     */
    private void resetTextView() {
        for (int i = 0; i < ivButtons.length; i++) {
            if (i == fragmentindex) {
                ivButtons[i].setImageResource(R.drawable.circle_b);
                if (i == 0) {
                    mainTabTextView.setText(getResources().getString(R.string.tui_jian));
                    mainTabIv.setImageResource(R.drawable.maintab_tuijian);
                } else if (i == 1) {
                    mainTabTextView.setText(getResources().getString(R.string.guan_zhu));
                    mainTabIv.setImageResource(R.drawable.maintab_guanzhu);
                } else if (i == 2) {
                    mainTabTextView.setText(getResources().getString(R.string.ou_yu));
                    mainTabIv.setImageResource(R.drawable.maintab_ouyu);
                }
            } else {
                ivButtons[i].setImageResource(R.drawable.circle_a);
            }
        }
    }

    @OnClick({R.id.mainTab_leftMenu, R.id.news, R.id.mainTab_leftClose, R.id.mainTab_iv, R.id.mainTab_textView, R.id.mainTab_llCurrentIndex, R.id.mainTab_ll_message, R.id.mainTab_ll_release, R.id.mainTab_ivOne, R.id.mainTab_ivTwo, R.id.mainTab_ivThree, R.id.mainTab_rlTiele, R.id.mainTab_viewPager, R.id.photo, R.id.name, R.id.linear, R.id.line, R.id.search, R.id.search_tv, R.id.friend, R.id.friend_tv, R.id.collect, R.id.collect_tv, R.id.world, R.id.world_tv, R.id.mean, R.id.mean_tv, R.id.footprint, R.id.footprint_tv, R.id.left, R.id.setting, R.id.setting_tv, R.id.translate, R.id.translate_tv, R.id.mainTab_drawerLayout, R.id.release})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainTab_textView:
                if (popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    reveal();
                } else {
                    popupwindow.showAsDropDown(mainTabIvOne);
                    mainTabLlCurrentIndex.setClickable(false);
                    mainTabLeftMenu.setVisibility(View.GONE);
                    mainTabLeftClose.setVisibility(View.VISIBLE);
                    mainTabLlMessage.setVisibility(View.GONE);
                    mainTabLlRelease.setVisibility(View.GONE);
                }
                break;
            case R.id.mainTab_leftMenu:
                mainTabDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.deleta_ll:
                mainTabTextView.setText(getResources().getString(R.string.tui_jian));
                mainTabIv.setImageResource(R.drawable.maintab_tuijian);
                fragmentindex = 0;
                popupwindow.dismiss();
                break;
            case R.id.cancel_ll:
                mainTabTextView.setText(getResources().getString(R.string.guan_zhu));
                mainTabIv.setImageResource(R.drawable.maintab_guanzhu);
                fragmentindex = 1;
                popupwindow.dismiss();
                break;
            case R.id.cancel_ll_ouyu:
                mainTabTextView.setText(getResources().getString(R.string.ou_yu));
                mainTabIv.setImageResource(R.drawable.maintab_ouyu);
                fragmentindex = 2;
                popupwindow.dismiss();
                break;
            case R.id.mainTab_leftClose:
                popupwindow.dismiss();
                break;
            case R.id.news:
                Intent newsIntent = new Intent(MainTabActivity.this, NewsActivity.class);
                startActivity(newsIntent);
                break;
            case R.id.mainTab_ll_release:
                Intent releaseIntent = new Intent(MainTabActivity.this,ReleaseActivity.class);
                startActivity(releaseIntent);
                break;
            case R.id.photo:
                break;
            case R.id.name:
                break;
            case R.id.search:
            case R.id.search_tv:
            Intent serchIntent  =  new Intent(MainTabActivity.this,SearchActivity.class);
                startActivity(serchIntent);
                break;
        }
        mainTabViewPager.setCurrentItem(fragmentindex);
        resetTextView();
    }
    /**
     *
     */
    private void initPopupwindow() {
        final LayoutInflater inflater = LayoutInflater.from(this);
        View vv = inflater.inflate(R.layout.test_dialog, null);
        LinearLayout delea = (LinearLayout) vv.findViewById(R.id.deleta_ll);
        LinearLayout back = (LinearLayout) vv.findViewById(R.id.cancel_ll);
        LinearLayout ouYU = (LinearLayout) vv.findViewById(R.id.cancel_ll_ouyu);
        popupwindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupwindow.setAnimationStyle(R.style.dialogAnim);
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        popupwindow.setOutsideTouchable(true);
        popupwindow.setFocusable(true);
        //popuwindow消失时回调方法
        popupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (popupwindow != null) {
                    reveal();
                }
            }
        });
        delea.setOnClickListener(this);
        back.setOnClickListener(this);
        ouYU.setOnClickListener(this);
    }
    /**
     * 控件的状态
     */
    private void reveal() {
        mainTabLeftMenu.setVisibility(View.VISIBLE);
        mainTabLeftClose.setVisibility(View.GONE);
        mainTabLlMessage.setVisibility(View.VISIBLE);
        mainTabLlRelease.setVisibility(View.VISIBLE);
        mainTabLlCurrentIndex.setClickable(true);
    }
    /**
     * 为回退键添加监听，用户退出登录状态
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - exitTime == 0 || currentTime - exitTime > 2000) {
                exitTime = System.currentTimeMillis();
                ToastUtils.showToast(getApplicationContext(), "亲，再按一次我回到桌面哦。。。");
                return false;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
