package com.itis.item;

import java.util.List;

/**
 * Created by sks on 2016/5/5.
 */
public class homeData {
    private String id;

    private String uid;

    private String content;

    private String ctime;

    private String longitude;

    private String latitude;

    private String fanyi;

    private String send_time;

    private String status;

    private String zid;

    private String tjstatus;

    private String z_num;

    private String y_num;

    private String m_num;

    private String user_pic;

    private String nickname;

    private String country_pic;

    private String comment_count;

    private String collection_count;

    private List<pic> pic ;

    private int country_partake;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setCtime(String ctime){
        this.ctime = ctime;
    }
    public String getCtime(){
        return this.ctime;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public void setFanyi(String fanyi){
        this.fanyi = fanyi;
    }
    public String getFanyi(){
        return this.fanyi;
    }
    public void setSend_time(String send_time){
        this.send_time = send_time;
    }
    public String getSend_time(){
        return this.send_time;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setZid(String zid){
        this.zid = zid;
    }
    public String getZid(){
        return this.zid;
    }
    public void setTjstatus(String tjstatus){
        this.tjstatus = tjstatus;
    }
    public String getTjstatus(){
        return this.tjstatus;
    }
    public void setZ_num(String z_num){
        this.z_num = z_num;
    }
    public String getZ_num(){
        return this.z_num;
    }
    public void setY_num(String y_num){
        this.y_num = y_num;
    }
    public String getY_num(){
        return this.y_num;
    }
    public void setM_num(String m_num){
        this.m_num = m_num;
    }
    public String getM_num(){
        return this.m_num;
    }
    public void setUser_pic(String user_pic){
        this.user_pic = user_pic;
    }
    public String getUser_pic(){
        return this.user_pic;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setCountry_pic(String country_pic){
        this.country_pic = country_pic;
    }
    public String getCountry_pic(){
        return this.country_pic;
    }
    public void setComment_count(String comment_count){
        this.comment_count = comment_count;
    }
    public String getComment_count(){
        return this.comment_count;
    }
    public void setCollection_count(String collection_count){
        this.collection_count = collection_count;
    }
    public String getCollection_count(){
        return this.collection_count;
    }
    public void setPic(List<pic> pic){
        this.pic = pic;
    }
    public List<pic> getPic(){
        return this.pic;
    }
    public void setCountry_partake(int country_partake){
        this.country_partake = country_partake;
    }
    public int getCountry_partake(){
        return this.country_partake;
    }
}
