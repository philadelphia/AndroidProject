package com.itis.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.itis.app.AppManager;
/**
 * activity基础类
 * 继承AutoLayoutActivity主要是为了px的屏幕适配
 * 邵光 on 2016/3/1 0001.
 */
public class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }
}
