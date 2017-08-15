package com.itis.activitys;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.itis.R;
import com.itis.utils.Connector;
import com.itis.utils.SharePreferenceUtil;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.register_name)
    EditText registerName;
    @Bind(R.id.register_psw)
    EditText registerPsw;
    @Bind(R.id.register_repsw)
    EditText registerRepsw;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.lin_register)
    LinearLayout linRegister;

    private String name, password, rePsw, id;
//    private TLSService tlsService;
//    private StrAccRegListener strAccRegListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setEdit();
    }
    private void setEdit() {
        /**
         * 点击空白位置 隐藏软键盘
         */
        linRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }
    @OnClick({R.id.back, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register_btn://注册
                name = registerName.getText().toString().trim();
                password = registerPsw.getText().toString().trim();
                rePsw = registerPsw.getText().toString().trim();
                String regex = "^[A-Za-z0-9_]+$";
                if (name == null || name.isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.login_toast_name));
                    return;
                } else if (password == null || password.isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.login_toast_psd));
                    return;
                } else if (rePsw == null || rePsw.isEmpty()) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.forget_toast_repwd));
                    return;
                } else if (rePsw.length() < 8) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_toast_lengh));
                } else if (!Pattern.compile(regex).matcher(name).matches() || Pattern.compile("^[0-9]+$").matcher(name).matches()) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_toast_name));
                } else if (rePsw.equals(password)) {
                    LoginData();
                } else {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.forget_toast_password));
                }
                break;
        }
    }

    private void LoginData() {
        id = SharePreferenceUtil.getPrefString(RegisterActivity.this,"id","");
        Tools.showProgressDialog(RegisterActivity.this, getResources().getString(R.string.loadingData));
        Connector.Register(name, rePsw, id, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1")) {
                        JSONObject json = jsonObject.getJSONObject("data");
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "name", name);
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "pass", password);
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "id", json.getString("id"));
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "imiu_id", json.getString("imiu_id"));
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "nickname", json.getString("nickname"));
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "email", json.getString("email"));
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "m_password", json.getString("m_password"));
                        //用于语言切换
                        String language = json.getString("language_type");
                        String name = ToastUtils.showcountry(language);
                        if (name.equals("null")) {
                            Locale locale = getResources().getConfiguration().locale;
                            language = locale.getLanguage();
                        } else {
                            language = name;
                        }
                        SharePreferenceUtil.setPrefString(RegisterActivity.this, "language_type", language);
                        Locale locale = new Locale(language);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(
                                config, null);
                        //注册腾讯
//                        onRegister();
                        // TODO: 2016/5/5
                    } else {
                        ToastUtils.showToast(RegisterActivity.this, jsonObject.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Tools.closeProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Tools.closeProgressDialog();
            }

            @Override
            public void onFinished() {
                Tools.closeProgressDialog();
            }
        });
    }

//    private void onRegister() {
//        tlsService = TLSService.getInstance();
//        strAccRegListener = new StrAccRegListener();
//        tlsService.TLSStrAccReg(name, password, strAccRegListener);
//    }
//
//    private class StrAccRegListener implements TLSStrAccRegListener {
//        @Override
//        public void OnStrAccRegSuccess(TLSUserInfo tlsUserInfo) {
//            Log.e("RegisterActivity", "" + tlsUserInfo);
//            // TODO: 2016/5/5
//        }
//
//        @Override
//        public void OnStrAccRegFail(TLSErrInfo tlsErrInfo) {
//            Log.e("RegisterActivity", "" + tlsErrInfo);
//        }
//
//        @Override
//        public void OnStrAccRegTimeout(TLSErrInfo tlsErrInfo) {
//            Log.e("RegisterActivity", "" + tlsErrInfo);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
