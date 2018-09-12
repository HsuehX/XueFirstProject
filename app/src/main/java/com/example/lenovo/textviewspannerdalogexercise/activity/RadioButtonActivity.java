package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.ButterKnife;

/**
 * Created by xue on 2018/1/26.
 */

public class RadioButtonActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        ButterKnife.bind(this);

    }
}
