package com.itis.activitys;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itis.R;
import com.itis.utils.Connector;
import com.itis.utils.SharePreferenceUtil;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import java.util.Locale;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class LoginActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_psw)
    EditText loginPsw;
    @Bind(R.id.login_register)
    TextView loginRegister;
    @Bind(R.id.login_forget)
    TextView loginForget;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.lin_login)
    LinearLayout linLogin;

    private String name,password,pass;
//    private TLSService tlsService;
//    public static PwdLoginListener pwdLoginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setEdit();
    }

    private void setEdit() {
        /**
         * 点击空白位置 隐藏软键盘
         */
        linLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    @OnClick({R.id.back, R.id.login_register, R.id.login_forget, R.id.login_btn})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login_register://注册账号
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forget://忘记密码

                break;
            case R.id.login_btn://登录
                name = loginName.getText().toString().trim();
                password = loginPsw.getText().toString().trim();
                if (name == null || name.isEmpty()) {
                    ToastUtils.showToast(LoginActivity.this, getResources().getString(R.string.login_toast_name));
                    return;
                } else if (password == null || password.isEmpty()) {
                    ToastUtils.showToast(LoginActivity.this, getResources().getString(R.string.login_toast_psd));
                    return;
                } else {
                    LoginIMiu();
                }
                break;
        }
    }
    private void onlogin() {
//        tlsService = TLSService.getInstance();
//        pwdLoginListener = new PwdLoginListener();
//
//        pass = SharePreferenceUtil.getPrefString(LoginActivity.this,"m_password","");
//        if(Common.IsEmptyOrNull(password)){
//
//        }else {
//            password     = pass;
//        }
//        tlsService.TLSPwdLogin(name, pass, pwdLoginListener);
    }
//    private  class PwdLoginListener implements TLSPwdLoginListener {
//        @Override
//        public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {
//            LoginSession session = new LoginSession();
//            session.setIdentifer(tlsUserInfo.identifier);
//            IMData data=new IMData(LoginActivity.this);
//            data.setUserName(tlsUserInfo.identifier);
//            // TODO: 2016/5/5
////            Appconfig.setSession(session);
//            if (name.isEmpty()||name.equals("null") ||name == "") {
////                startActivity(new Intent(LoginActivity.this, PerfectActivity.class));
////                LoginActivity.this.finish();
//            }else {
////                startActivity(new Intent(LoginActivity.this, Main_TabActivity.class));
////                LoginActivity.this.finish();
//            }
//        }
//        @Override
//        public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {
//            Log.e("loginActivity", 3333333 + "2");
//        }
//
//        @Override
//        public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {
//            Log.e("loginActivity", 444444 + "2");
//        }
//        @Override
//        public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {
//            ToastUtils.showToast(LoginActivity.this,tlsErrInfo.Msg);
//        }
//        @Override
//        public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {
//            Log.e("loginActivity", 6666666 + "2");
//        }
//    }
    private void LoginIMiu() {
        Tools.showProgressDialog(LoginActivity.this, getResources().getString(R.string.loadingData));
        Connector.Login(name, password, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1")) {
                        JSONObject json = jsonObject.getJSONObject("data");
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "id", json.getString("id"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "name", name);
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "pass", password);
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "id", json.getString("id"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "imiu_id", json.getString("imiu_id"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "nickname", json.getString("nickname"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "email", json.getString("email"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "user_pic", json.getString("user_pic"));
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "m_password", json.getString("m_password"));
                        //用于语言切换
                        String language = json.getString("language_type");
                        String name = ToastUtils.showcountry(language);
                        if (name.equals("null")) {
                            Locale locale = getResources().getConfiguration().locale;
                            language = locale.getLanguage();
                        } else {
                            language = name;
                        }
                        SharePreferenceUtil.setPrefString(LoginActivity.this, "language_type", language);
                        Locale locale = new Locale(language);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(
                                config, null);
                        onlogin();

                    } else {
                        ToastUtils.showToast(LoginActivity.this, jsonObject.getString("message"));
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
