package com.itis.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToastUtils {
	public static void showToast(Context ctx, String text) {
		Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
	}

	//返回一个列表对话框
	public static AlertDialog.Builder getListDialogBuilder(Context context,
														   String[] items, String title, DialogInterface.OnClickListener clickListener) {
		return new AlertDialog.Builder(context).setTitle(title).setItems(items, clickListener);

	}

	/**
	 * 时间
	 * @param times
	 * @return
	 */

	public static String showTime(String times) {
		long t = Long.parseLong(times);
		long time = System.currentTimeMillis() - (t * 1000);
		long mill = (long) Math.ceil(time / 1000);//秒前
		long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前
		long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时
		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前
		if (day >= 3) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long lcc_time = Long.valueOf(times);
			String re_StrTime1 = sdf.format(new Date(lcc_time * 1000L));
			return re_StrTime1;
		} else if (day >= 2 && day < 3) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			long lcc_time = Long.valueOf(times);
			String re_StrTime1 = sdf.format(new Date(lcc_time * 1000L));
			return "前天" + re_StrTime1;
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss");
				long lcc_time = Long.valueOf(times);
				String re_StrTime1 = sdf.format(new Date(lcc_time * 1000L));
				return "昨天" + re_StrTime1;
			} else {
				return hour + "小时";
			}
		} else if (minute - 1 > 0) {
			if (minute == 60) {
				return "1小时";
			} else {
				return minute + "分钟";
			}
		} else if (mill - 1 > 0) {
			if (mill == 60) {
				return "1分钟";
			} else {
				return mill + "秒";
			}
		} else {
			return "刚刚";
		}
	}
	/**
	 * 国家
	 * @param language
	 * @return
	 */

	public static String showcountry(String language) {
		if (language.equals("中文")) {
			return language = "zh-Hans";
		} else if (language.equals("Deutsch")) {
			return language = "de";
		} else if (language.equals("Français")) {
			return	language = "fr";
		} else if (language.equals("русский")) {
			return	language = "ru";
		}else if (language.equals("日本語")) {
			return	language = "ja";
		} else if (language.equals("한국의")) {
			return	language = "ko";
		} else if (language.equals("English")) {
			return	language = "en";
		}else if (language.isEmpty() ||language.equals("null")) {
			return language="null";
		}
		return language = "zh";
	}
}
