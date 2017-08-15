package com.itis.app;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * 邵光
 * Created by sks on 2016/4/27.
 */
public class MyAplication extends Application{

    private static MyAplication instance = null;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this.getApplicationContext();
        x.Ext.init(this);
    }

    public static MyAplication getInstance() {
        if (instance == null) {
            synchronized (MyAplication.class) {
                instance = new MyAplication();
            }
        }
        return instance;
    }

    public static Context getContext(){
        return context;
    }


}
