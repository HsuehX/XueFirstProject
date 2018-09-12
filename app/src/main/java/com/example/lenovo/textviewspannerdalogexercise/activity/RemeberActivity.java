package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;

/**
 * Created by xue on 2018/1/23.
 */

public class RemeberActivity extends Activity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private TextView login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remeber);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.login_username_et);
        passwordEdit = (EditText) findViewById(R.id.login_password_et);
        login = (TextView) findViewById(R.id.login_tv);
        rememberPass = (CheckBox) findViewById(R.id.login_password_cb);
        boolean isRemenber = pref.getBoolean("remember_password", false);
        if (isRemenber) {
            //将账号和密码都设置到文本中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //如果账号是admin且密码是123456就认为登陆成功
                if (account.equals("123") && password.equals("123")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(RemeberActivity.this, SwipeListViewDataActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RemeberActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}