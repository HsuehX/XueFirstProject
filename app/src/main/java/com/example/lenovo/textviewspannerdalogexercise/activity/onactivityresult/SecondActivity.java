package com.example.lenovo.textviewspannerdalogexercise.activity.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/6/25.
 */

public class SecondActivity extends Activity {
    @Bind(R.id.returnbt1)
    Button returnbt1;
    @Bind(R.id.returnbt2)
    Button returnbt2;
    @Bind(R.id.et_second)
    EditText et_second;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_second);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.returnbt1, R.id.returnbt2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnbt1:
                //使用Intent对象得到FirstActivity传递来的参数
                Intent intentFirst = getIntent();//获取上一个页面的参数
                String res = et_second.getText().toString();
                intentFirst.putExtra("resultSecond1", res);// 把数据塞入intent里面
                SecondActivity.this.setResult(0, intentFirst);// 跳转回原来的activity
                SecondActivity.this.finish();// 一定要结束当前activity
                break;
            case R.id.returnbt2:
                Intent intentTwo = getIntent();//获取上一个页面的参数
                intentTwo.putExtra("resultSecond2", "验证result code");
                setResult(1, intentTwo);
                finish();
                break;
            default:
                break;
        }
    }
}
