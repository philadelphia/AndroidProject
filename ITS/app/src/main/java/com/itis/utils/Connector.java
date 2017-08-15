package com.itis.utils;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by sks on 2016/5/5.
 * 接口公共类
 */
public class Connector {
    static String uid= "9999";
//    static String uid= SharePreferenceUtil.getPrefString(MyAplication.get);

    /**
     * 添加机器码
     */
    public static void Machine(String machine,String machine_language,String versionCode,String phone_model, Callback.CommonCallback<String> RegisterCallback) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("machine", machine);
        map.put("machine_language", machine_language);
        map.put("version_number", versionCode);
        map.put("phone_model", phone_model);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.machine);
        rp.addParameter("machine", machine);
        rp.addParameter("machine_language", machine_language);
        rp.addParameter("version_number", versionCode);
        rp.addParameter("phone_model", phone_model);
        rp.addParameter("sign", sign);
        x.http().post(rp, RegisterCallback);
    }

    /**
     *注册
     */
    public static void Register(String username,String password,String id, Callback.CommonCallback<String> RegisterCallback) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("uid", uid);
        map.put("username", username);
        map.put("password", password);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.register);
        rp.addParameter("uid", uid);
        rp.addParameter("username", username);
        rp.addParameter("password", password);
        rp.addParameter("sign", sign);
        x.http().post(rp, RegisterCallback);
    }

    /**
     * 登录
     */
    public static void Login(String username,String password, Callback.CommonCallback<String> LoginCallback) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.register);
        rp.addParameter("username", username);
        rp.addParameter("password", password);
        rp.addParameter("sign", sign);
        x.http().post(rp, LoginCallback);
    }
    /**
     * 世界圈的列表
     */
    public static void world_cricle_list(String page,String identification,Callback.CommonCallback<String> worldCallback ){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("page", page);
        map.put("identification", identification);
        map.put("uid",uid);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.world_cricle_list);
        rp.addParameter("page", page);
        rp.addParameter("identification", identification);
        rp.addBodyParameter("uid", uid);
        rp.addParameter("sign", sign);
        x.http().post(rp, worldCallback);
    }
    /**
     * 关注
     */
    public static void follow(String sid,Callback.CommonCallback<String> followCallback ){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("uid", uid);
        map.put("sid",sid);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.follow);
        rp.addBodyParameter("uid", uid);
        rp.addParameter("sid", sid);
        rp.addParameter("sign", sign);
        x.http().post(rp, followCallback);
    }
    /**
     * 取消关注
     */
    public static void followOff(String sid,Callback.CommonCallback<String> followOffCallback ){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("uid", uid);
        map.put("sid",sid);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.followOff);
        rp.addBodyParameter("uid", uid);
        rp.addParameter("sid", sid);
        rp.addParameter("sign", sign);
        x.http().post(rp, followOffCallback);
    }
    /**
     * 发布世界圈
     */
    public static void sendWorldCircle(String content,String picNames,Callback.CommonCallback<String> ReleaseCallback){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("content", content);
        map.put("uid", uid);
        map.put("pic",picNames);
        map.put("longitude", Constant.longitude + "");
        map.put("latitude", Constant.latitude + "");
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.send_world_circle);
        rp.addParameter("content",content);
        rp.addParameter("uid", uid);
        rp.addParameter("pic",picNames);
        rp.addParameter("longitude", Constant.longitude);
        rp.addParameter("latitude", Constant.latitude);
        rp.addParameter("sign",sign);
        x.http().post(rp, ReleaseCallback);
    }
    /**
     * 有意思或没意思
     */
    public static void meaning(String wid,String num,Callback.CommonCallback<String> meanCallback){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("uid", uid);
        map.put("wid",wid);
        map.put("num",num);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.meaning);
        rp.addBodyParameter("uid", uid);
        rp.addParameter("wid", wid);
        rp.addParameter("num", num);
        rp.addParameter("sign", sign);
        x.http().post(rp, meanCallback);
    }
    /**
     *世界圈评论
     */
    public static void comment(String wid,String sid,String content,Callback.CommonCallback<String> commentCallback){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("uid", uid);
        map.put("wid",wid);
        map.put("sid",sid);
        map.put("content",content);
        String sign = GetMD5.getMD5(map);
        RequestParams rp = new RequestParams(API.comment);
        rp.addBodyParameter("uid", uid);
        rp.addParameter("wid", wid);
        rp.addParameter("sid", sid);
        rp.addBodyParameter("content", content);
        rp.addParameter("sign", sign);
        x.http().post(rp, commentCallback);
    }
}
