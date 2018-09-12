package com.example.lenovo.textviewspannerdalogexercise.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * Created by xue on 2018/3/27.
 */

public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册广播  网上方法
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lioil.win.close");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //注销广播
                unregisterReceiver(this);
                ((Activity) context).finish();
            }
        }, filter);
    }

    public void close() {
        //发送广播
        Intent intent = new Intent();
        intent.setAction("com.lioil.win.close");
        sendBroadcast(intent);
        finish();
    }
}
