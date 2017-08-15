package com.itis.utils;

/**
 *
 * Created by sks on 2016/3/27.
 */
public class Common {

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean IsEmptyOrNull(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }
}
