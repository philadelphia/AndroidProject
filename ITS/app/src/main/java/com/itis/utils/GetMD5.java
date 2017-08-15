package com.itis.utils;

import java.util.Map;

/**
 * 邵光
 * 进行md5加密封装类
 * Created by sks on 2016/3/27.
 */
public class GetMD5 {
    public static String getMD5(Map<String, String> map){
        Map<String, String> resultMap = SortMap.sortMapByKey(map);
        String strA = "";
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            strA += entry.getKey() + "=" + entry.getValue() + "&";
        }
        String strB = strA + "rand_str=" + Constant.rand_str;
        String sign = MD5.parseStrToMd5U32(strB);
        return sign;
    }
}
