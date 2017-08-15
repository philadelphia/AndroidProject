package com.itis.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 * 加载进度提示框
 */
public class Tools {
	private static ProgressDialog dialog;

	public static void showProgressDialog(Context context, String msg) {
		try {
			if (dialog == null) {
				dialog = new ProgressDialog(context);
				dialog.setMessage(msg);
				dialog.setCanceledOnTouchOutside(false);
				dialog.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeProgressDialog() {
		try {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
				dialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
