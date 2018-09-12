package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xue on 2018/3/21.
 */

public class GifActivity extends Activity {
    @Bind(R.id.gif_tv)
    ImageView gif_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Glide.with(GifActivity.this).load(R.drawable.aaa).diskCacheStrategy(DiskCacheStrategy.ALL).into(gif_tv);
    }
}
