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

public class ThridActivity extends Activity {
    Button returnbt1;
    @Bind(R.id.returnbt3)
    Button returnbt3;
    @Bind(R.id.et_third)
    EditText et_second;
    @Bind(R.id.tv_third)
    TextView tv_third;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_third);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.returnbt3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnbt3:
                Intent intentThird = getIntent();//获取上一个页面的参数
                String res = et_second.getText().toString();
                intentThird.putExtra("resultThird", res);
                ThridActivity.this.setResult(RESULT_OK, intentThird);
                ThridActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
