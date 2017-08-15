package com.itis.activitys;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itis.R;
import com.itis.item.Appconfig;
import com.itis.utils.Connector;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/12.
 */
public class PopDialogActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_comment_quxiao)
    TextView tvCommentQuxiao;
    @Bind(R.id.tv_comment_pinglun)
    TextView tvCommentPinglun;
    @Bind(R.id.tv_comment_fasong)
    TextView tvCommentFasong;
    @Bind(R.id.rl_out)
    LinearLayout rlOut;
    @Bind(R.id.tv_xian)
    TextView tvXian;
    @Bind(R.id.tv_content)
    EditText tvContent;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_popuwindow);
        ButterKnife.bind(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
    @OnClick({R.id.tv_comment_quxiao, R.id.tv_comment_fasong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_comment_quxiao:
                finish();
                break;
            case R.id.tv_comment_fasong:
                content = tvContent.getText().toString().trim();
                if (content == null || content.isEmpty()) {
                    ToastUtils.showToast(PopDialogActivity.this, getResources().getString(R.string.publish_content));
                    return;
                }else {
                    getData();
                }
                break;
        }
    }
    /**
     * 评论
     */
    private void getData() {
        Tools.showProgressDialog(PopDialogActivity.this, getResources().getString(R.string.loadingData));
        Connector.comment(Appconfig.getComms().getWid(),Appconfig.getComms().getSid() , content, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
             try {
                 JSONObject jsonObject = new JSONObject(result);
                 if(jsonObject.getString("code").equals("1")){
                     ToastUtils.showToast(PopDialogActivity.this,jsonObject.getString("message"));
                     finish();
                 }else{
                     ToastUtils.showToast(PopDialogActivity.this,jsonObject.getString("message"));
                 }
             }catch (JSONException e){
                 e.printStackTrace();
             }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
