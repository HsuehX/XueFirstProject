package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.utils.Aes;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xue on 2018/2/28.
 */

public class AesEncryptionActivity extends Activity{

    private EditText mInputET;
    private TextView mShowEncryputTV;
    private TextView mShowInputTV;
    private static final String PASSWORD_STRING = "2F06ADBDC3F040DA9AC1620E0FFFEA2E";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes_encryption);
        ButterKnife.bind(this);
        mInputET = (EditText) findViewById(R.id.ase_input);
        mShowEncryputTV = (TextView) findViewById(R.id.show_oringe_encrypt);
        mShowInputTV = (TextView) findViewById(R.id.show_ase_encrypt);
    }


    /**
     * 加密
     *
     * @param view
     */
    public void encrypt(View view) {
        String inputString = mInputET.getText().toString().trim();
        if (inputString.length() == 0) {
            Toast.makeText(this, "请输入要加密的内容", Toast.LENGTH_SHORT).show();
            return;
        }
        String encryStr = Aes.encrypt(PASSWORD_STRING, inputString);
        mShowInputTV.setText(encryStr);
        encryStr.length();
    }

    /**
     * 解密
     *
     * @param view
     */
    public void decrypt(View view) {
        String encryptString = mShowInputTV.getText().toString().trim();
        if (encryptString.length() == 0) {
            Toast.makeText(this, "解密字符串不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String decryStr = Aes.decrypt(PASSWORD_STRING, encryptString);
        mShowEncryputTV.setText(decryStr);
    }

}
