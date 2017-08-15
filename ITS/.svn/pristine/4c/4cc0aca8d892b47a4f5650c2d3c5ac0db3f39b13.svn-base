package com.itis.app;

import android.app.Activity;
import android.content.Context;

import com.itis.utils.ACache;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 项目工具类
 * 邵光 2016年3月1日11:23:03
 */
public class AppManager {

    private static Stack<Activity> activitysStack = null;
    private static AppManager instance = null;
    public static List<Activity> activityList = new ArrayList<Activity>();
    private static List<String> keys=null;
    private ACache mCache;

    public AppManager() {
    }

    public static AppManager getAppManager() {
        if (null == instance) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 把所有的activity添加到集合中
     */
    public void addActivity(Activity activity) {
        if (null == activitysStack) {
            activitysStack = new Stack<Activity>();
        }
        activitysStack.add(activity);
    }

    /**
     * 把所有缓存key添加进来
     * @param CacheKey
     */
    public void addCacheKey(String CacheKey){
        if(null ==keys){
            keys=new ArrayList<String>();
        }
        keys.add(CacheKey);
    }

    public void finishCacheKeys(Activity context){
        mCache = ACache.get(context);
        try {
            //TODO:开始加载动画
            for(int i=0;i<keys.size();i++){
                if(keys.get(i)!=null){
                    mCache.remove(keys.get(i));
                }
            }
            //TODO:结束加载动画
            //TODO:清楚结束提示Toast
        }catch (Exception e){
        }
    }
    /**
     * 销毁集合中的所有activity
     */
    public void finishAllActivity() {
        try {
            for (int i = 0; i < activitysStack.size(); i++) {
                if (activitysStack.get(i) != null) {
                    activitysStack.get(i).finish();
                }
            }
            activitysStack.clear();
        } catch (Exception e) {
        }
    }

    /**
     * 返回到桌面时候退出并销毁应用以及内存
     */
    public void AppExit(Context context){
        try {
            finishAllActivity();
            System.exit(0);
        }catch (Exception ee){
        }
    }
}
