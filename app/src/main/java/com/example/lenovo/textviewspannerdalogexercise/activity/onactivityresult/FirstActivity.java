package com.example.lenovo.textviewspannerdalogexercise.activity.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/6/25.
 */

public class FirstActivity extends Activity {
    @Bind(R.id.bt2)
    Button btn2;
    @Bind(R.id.bt3)
    Button btn3;
    @Bind(R.id.tv_show)
    TextView tv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_first);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt2, R.id.bt3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt2:
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //这里第二个参数的值必须要大于等于0，你可以自己设定数值，该值是用来标记你要跳转并返回值的Activity。
                // 比如这里我设定的是以就是说明A.Activity跳转到B.Activity的标记为11，
                // 若A.Activity跳转到C.Activity那就不能再设定为11了，可以写除了1意外的所有正整数。
                startActivityForResult(intent, 11);
                break;
            case R.id.bt3:
                Intent intentTwo = new Intent(FirstActivity.this, ThridActivity.class);
                startActivityForResult(intentTwo, 22);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 11:
                switch (resultCode) {
                    case 0:
                        String resultFirst = data.getStringExtra("resultSecond1");
                        tv_show.setText(resultFirst);
                        Toast.makeText(this, "第2个activity的requestCode的值为" + requestCode, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        String resultFirst2 = data.getStringExtra("resultSecond2");
                        tv_show.setText(resultFirst2);
                        Toast.makeText(this, "第2个activity的requestCode的值为" + requestCode, Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                break;
            case 22:
                switch (resultCode) {
                    case RESULT_OK:
                        String resultstrSecond2 = data.getStringExtra("resultThird");
                        tv_show.setText(resultstrSecond2);
                        Toast.makeText(this, "第2个activity的requestCode的值为" + requestCode, Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
