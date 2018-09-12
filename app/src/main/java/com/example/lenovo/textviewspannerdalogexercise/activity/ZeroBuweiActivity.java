package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.SecondActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/3/6.
 */

public class ZeroBuweiActivity extends Activity {
    @Bind(R.id.zerobuweit_et)
    EditText zerobuweit_et;
    @Bind(R.id.zerobuweit_end_et)
    EditText zerobuweit_end_et;
    @Bind(R.id.zerobuweit_show_tv)
    TextView zerobuweit_show_tv;
    @Bind(R.id.show_tv)
    TextView show_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zerobuwei);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String text = zerobuweit_et.getText().toString();
//        changeFormat(text);
    }

    @OnClick({R.id.show_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_tv:
                String text = zerobuweit_et.getText().toString();
                String endText = zerobuweit_end_et.getText().toString();
//                show_tv.setText(changeFormat(text));
//                zerobuweit_show_tv.setText(getTimeMillis(text) + "");
                zerobuweit_show_tv.setText(getTimeDifference(text, endText) + "min");
                break;
            default:
                break;
        }
    }

    /**
     * 换格式
     */
    private String changeFormat(String text) {
        String str2 = null;
        if (!TextUtils.isEmpty(text)) {
            DecimalFormat df = new DecimalFormat("00000");
            str2 = df.format(Integer.parseInt(text));
            System.out.println(str2);
        }
        return str2;
    }

    private static long getTimeMillisThree(String strTime) {
        long returnMillis = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }

    private static long getTimeMillis(String strTime) {
        long returnMillis = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }

    public static String getTimeDifference(String startTime, String endTime) {
        long longStart = 0;
        long longEnd = 0;
        if (startTime.length() == 5 && endTime.length() == 4) {
            longStart = getTimeMillisThree(startTime);
            longEnd = getTimeMillis(endTime);
        } else if (startTime.length() == 4 && endTime.length() == 5) {
            longStart = getTimeMillis(startTime);
            longEnd = getTimeMillisThree(endTime);
        } else if (startTime.length() == 4 && endTime.length() == 4) {
            longStart = getTimeMillisThree(startTime);
            longEnd = getTimeMillisThree(endTime);
        } else if (startTime.length() == 5 && endTime.length() == 5) {
            longStart = getTimeMillis(startTime);
            longEnd = getTimeMillis(endTime);
        }

//        long longStart = getTimeMillisThree(startTime);
//        long longEnd = getTimeMillis(endTime);
        long longExpend = longEnd - longStart;
        long longMinutes = longExpend / (60 * 1000);

        return longMinutes + "";
    }
}
