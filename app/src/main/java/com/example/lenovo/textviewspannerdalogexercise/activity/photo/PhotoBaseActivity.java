package com.example.lenovo.textviewspannerdalogexercise.activity.photo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by xue on 2018/4/19.
 * 拍照裁剪的基类
 * 网上的这个例子好像是用MVP写的
 */

public abstract class PhotoBaseActivity extends ActionBarActivity {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public Context mContext = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
//        PhotoApplication.getInstance().mActivityStack.addActivity(this);
        setOverflowShowingAlways();
        super.onCreate(savedInstanceState);

        initContentView();//初始化布局
        ButterKnife.bind(this);
        init();
        initViews();//初始化View
        initEvents();//初始化事件
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 设置总是显示溢出菜单
     */
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ButterKnife.unbind(this);
//    }
//
//    public void finish() {
//        super.finish();
////        PhotoApplication.getInstance().mActivityStack.removeActivity(this);
//    }

    /**
     * 初始化布局
     */
    protected abstract void initContentView();

    /**
     * 初始化
     */
    protected void init() {
    }

    /**
     * 初始化View
     */
    protected abstract void initViews();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();
}
