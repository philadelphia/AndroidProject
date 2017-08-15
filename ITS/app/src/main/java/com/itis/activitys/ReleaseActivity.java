package com.itis.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.itis.R;
import com.itis.adapter.ReleaseAdapter;
import com.itis.app.MyAplication;
import com.itis.item.LocalMedia;
import com.itis.utils.CheckNetwork;
import com.itis.utils.Connector;
import com.itis.utils.SharePreferenceUtil;
import com.itis.utils.ToastUtils;
import com.itis.utils.Tools;
import com.itis.view.GridViewScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseActivity extends AppCompatActivity {

    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.send)
    TextView send;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.second)
    TextView second;
    @Bind(R.id.relase_context)
    EditText relaseContext;
    @Bind(R.id.relase_gridview)
    GridViewScrollView relaseGridview;
    @Bind(R.id.photo)
    ImageView photo;
    @Bind(R.id.camera)
    ImageView camera;
    @Bind(R.id.emoji)
    ImageView emoji;
    @Bind(R.id.position)
    RadioButton position;
    private ReleaseAdapter relesaseAdapter;
    private List<String> mResults;
    private String textLabel;
    private String uploadArea = "";   //上传的地理坐标
    private String picNames; //图片名字集合
    private static final String TAG = "ReleaseActivity";
    private ArrayList<String> picsResult;
    Handler handler;
    private int index = 0;
    private final int TAKEFROMCEMERA = 100;
    private String uuid="9999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);

        //initView();
    }


    private void initView() {
        uuid = SharePreferenceUtil.getPrefString(ReleaseActivity.this, "id", "");
    }
    @OnClick({R.id.cancel, R.id.send, R.id.photo, R.id.camera, R.id.emoji, R.id.position})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.send:
                picNames = "";
                Tools.showProgressDialog(ReleaseActivity.this, getResources().getString(R.string.loadingSubmit));
                if (CheckNetwork.checkNetworkState(ReleaseActivity.this) == true) {
                    if (TextUtils.isEmpty(relaseContext.getText().toString())) {
                        ToastUtils.showToast(ReleaseActivity.this, getResources().getString(R.string.publish_content));
                        Tools.closeProgressDialog();
                        return;
                    }
//                    if (Common.IsEmptyOrNull(uploadArea)) {
//                        uploadArea = "ok";
//                    }
                    if (picsResult!=null) {
                        for (int i = 0; i < picsResult.size(); i++) {
                            Log.e(TAG, "picsResult===" + picsResult.size());
//                            Permission.verifyStoragePermissions(ReleaseActivity.this);
                            UploadPicToAli(picsResult.get(i));
                        }
                    }





                    if (picNames != null && picNames.length() != 0) {
                        picNames = picNames.substring(0, picNames.length() - 1);
                    }else {
                        picNames="";
                    }
                    Log.e(TAG, "提交的数据===" + relaseContext.getText().toString().trim()  + " ,picNames=== " + picNames);
                    Connector.sendWorldCircle(relaseContext.getText().toString().trim(), picNames, new org.xutils.common.Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                if (code.equals("1")) {
                                    ToastUtils.showToast(ReleaseActivity.this, message);
                                    ReleaseActivity.this.finish();
                                } else {
                                    ToastUtils.showToast(ReleaseActivity.this, jsonObject.getString("message"));
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




                    break;
                }

            case R.id.photo:
                Intent photoIntent = new Intent(ReleaseActivity.this,ImageSelectorActivity.class);
                if (picsResult !=null && picsResult.size()>0){
                    photoIntent.putExtra(ImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, picsResult);
                }

                startActivityForResult(photoIntent, ImageSelectorActivity.REQUEST_IMAGE);
//                Intent photoIntent = new Intent(ReleaseActivity.this, PhotoPickerActivity.class);
//                photoIntent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, false);
//                photoIntent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, 1);
//                photoIntent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 9);
//                startActivityForResult(photoIntent, Constant.photoPickerResult);
                break;
            case R.id.camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//android.media.action.IMAGE_CAPTURE
                startActivityForResult(intent, TAKEFROMCEMERA);
                break;
            case R.id.emoji:

                break;
            case R.id.position:

                break;
        }
    }
    public void UploadPicToAli(String path) {  //上传到阿里服务器
        Log.e(TAG,"UploadPicToAli/len---"+path.length());
        Log.e(TAG,"UploadPicToAli---"+path);
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("dQP8rR8redII30AO", "FhD1jtLnWWVuJsy7uJdJM97rDRb1Az");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(10 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(10 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(9); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(5); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(MyAplication.getContext(), endpoint, credentialProvider, conf);
        long randomName = System.currentTimeMillis();
        final String picName = "world_pic/" + uuid + "/" + randomName + ".png";

        picNames += picName + ",";
        Log.e(TAG, "onSuccess/228/picName----" + picName+"------"+picNames);
        PutObjectRequest put = new PutObjectRequest("imiunew", picName, path);
//        try {
//            PutObjectResult putResult = oss.putObject(put);
//            Log.e(TAG,"putResult------"+putResult.toString());
//        } catch (ClientException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            Log.e(TAG,"RequestId---"+ e.getRequestId());
//            Log.e(TAG,"ErrorCode-----"+ e.getErrorCode());
//            Log.e(TAG,"HostId"+e.getHostId());
//            Log.e(TAG,"RawMessage"+ e.getRawMessage());
//        }
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest putObjectRequest, long l, long l1) {
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                Log.i(TAG, "onSuccess");
                Log.i(TAG, "onSuccess/putObjectRequest---"+putObjectRequest);
                Log.i(TAG, "onSuccess/putObjectResult---" + putObjectResult.toString());
                Log.e(TAG, "onSuccess/228/picName----" + picName);
                index++;


            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Log.i(TAG, "el= " + e1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKEFROMCEMERA && resultCode == RESULT_OK) {
            Uri uri = data.getData();//大图 内存比较大的时候回返回地址
            if (uri != null) {
                Log.e("自定义标签", "onActivityResult: " + uri);
                Cursor query = getContentResolver().query(uri, null, null, null, null);//只有一张图片
                query.moveToFirst();
                String imgno = query.getString(0);
                String imgpath = query.getString(1);// 图片的路径 图片保存的路径/ sdcard/xxxx/xxxxx
                String imgsize = query.getString(2);//图片的大小
                String imgname = query.getString(3);//图片的名字
//                BitmapFactory.decodeFile()
//                try {
//                    BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));//直接将 uri 对应的文件或者内容转成流然后转成图片
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
            } else {//如果返回的不是 uri 那么就是图片,图片会比较小
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                Log.e("自定义标签", "onActivityResult: " + bitmap);
                //iv.setImageBitmap(bitmap);
            }
        }else if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {

            Log.i(TAG, "onActivityResult: "+data);

            if(data!=null && !data.equals("")) {
                picsResult = (ArrayList<String>) data.getStringArrayListExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                Log.i(TAG, "onActivityResult: " + picsResult.size());
                getImage();
            }
        }
    }

    private void getImage() {
        relesaseAdapter = new ReleaseAdapter(ReleaseActivity.this, picsResult);
        relaseGridview.setAdapter(relesaseAdapter);
        relaseGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<LocalMedia> selectList = new ArrayList<LocalMedia>();
                if (picsResult.isEmpty() || picsResult.size() == 0) {

                } else {
                    for (int i = 0; i < picsResult.size(); i++) {
                        LocalMedia select = new LocalMedia(picsResult.get(i));
                        selectList.add(select);
                    }
                    startPreview(selectList, position);
                }
            }
        });
    }
    public void startPreview(List<LocalMedia> previewImages, int position) {
        ImagePreviewActivity.startPreview(this, previewImages, previewImages, 9, position);
    }

}
