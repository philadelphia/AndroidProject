package com.itis;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.itis.activitys.BaseActivity;
import com.itis.activitys.MainTabActivity;
import com.itis.utils.Connector;
import com.itis.utils.SharePreferenceUtil;
import com.itis.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private String  id,machine,language,versionCode,phone_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(1800);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getMachine();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void getMachine() {
        try {
            machine = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
            language = SharePreferenceUtil.getPrefString(MainActivity.this, "language_type", "");
            PackageInfo info = this.getPackageManager().getPackageInfo(MainActivity.this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            // 当前版本的版本号
            versionCode = info.versionCode+"";
            //手机型号
            phone_model = android.os.Build.MODEL;
            Log.e("phone_model",phone_model);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (language.isEmpty()||language.equals("null")){
            Locale locale = getResources().getConfiguration().locale;
            language = locale.getLanguage();
        }
        Connector.Machine(machine,language,versionCode,phone_model, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1")) {
                        id = jsonObject.getString("data");
                        SharePreferenceUtil.setPrefString(MainActivity.this, "id", id);
                        if (id.isEmpty()||id.equals("")){
                        }else{
                            startActivity(new Intent(MainActivity.this, MainTabActivity.class));
                            MainActivity.this.finish();
                        }
                    } else {
                        ToastUtils.showToast(MainActivity.this, jsonObject.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("id",ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
