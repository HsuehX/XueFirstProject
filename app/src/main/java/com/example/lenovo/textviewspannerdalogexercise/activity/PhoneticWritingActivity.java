package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.msi.OnGetRecordResultListener;
import com.example.lenovo.textviewspannerdalogexercise.view.msi.RecordDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/4/16.
 */

public class PhoneticWritingActivity extends Activity {
    @Bind(R.id.edit_nameaddress_phone_send)
    EditText mPhoneSendET;
    @Bind(R.id.iv_record)
    ImageView iv_record;

    private RecordDialog mRecordDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outside_activity_nameaddress_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initRecordDialog();
    }


    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    private void initRecordDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            } else {
                Toast.makeText(getApplicationContext(), "权限已申请", Toast.LENGTH_SHORT);

                mRecordDialog = new RecordDialog(PhoneticWritingActivity.this, new OnGetRecordResultListener() {
                    @Override
                    public void onGetResult(String result) {
                        mPhoneSendET.setText(result);
                        mRecordDialog.dismissDialog();
                    }
                });
            }
        }
//        // 初始化语音引擎
//        SpeechUtility.createUtility(NameAddressInfoActivity.this, SpeechConstant.APPID + "=5acc6f25");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "权限已申请", Toast.LENGTH_SHORT);
                mRecordDialog = new RecordDialog(PhoneticWritingActivity.this, new OnGetRecordResultListener() {
                    @Override
                    public void onGetResult(String result) {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
                        mRecordDialog.dismissDialog();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "权限已申请", Toast.LENGTH_SHORT);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick({R.id.iv_record})
    public void countWeigthOnClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_record) {
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            mRecordDialog.createDialog(display);
            Toast.makeText(getApplicationContext(), "2qw3e4dr", Toast.LENGTH_SHORT);
        }
    }
}
