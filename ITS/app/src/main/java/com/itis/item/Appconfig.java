package com.itis.item;


import java.util.List;

/**
 * Created by 瑶瑶 on 2016/3/18.
 */
public class Appconfig {

    //首页数据
    public static List<homeData> homeDatas;
    //评论
    public static comm comms;
    public static List<homeData> getHomeDatas() {
        return homeDatas;
    }
    public static void setHomeDatas(List<homeData> homeDatas) {
        Appconfig.homeDatas = homeDatas;
    }
    public static comm getComms() {
        return comms;
    }

    public static void setComms(comm comms) {
        Appconfig.comms = comms;
    }

}