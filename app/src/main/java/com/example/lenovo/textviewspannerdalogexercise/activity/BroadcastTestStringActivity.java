package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;

/**
 * Created by xue on 2019/4/7.
 */

public class BroadcastTestStringActivity extends Activity {
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private TextView mReceiverTestTv;
    private String result;
    DynamicReceiver dynamicReceiver;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test_string);
        init();
    }

    private void init() {
        initView();
        intentFilter = new IntentFilter();
        intentFilter.addAction("test1");
        broadcastReceiver = new MyReceiver();
        registerReceiver(broadcastReceiver, intentFilter);

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("test1");
//        intentFilter.addAction("com.android.carpower.intent.standbyentre");
//        dynamicReceiver = new DynamicReceiver();
//        registerReceiver(dynamicReceiver, intentFilter);
//
//        localReceiver = new LocalReceiver();
//        //获取实例
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //注册本地广播监听器
//        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                mReceiverTestTv.setText("" + result);
//            }
//        }, 5000);   //5秒

    }

    private void initView() {
        mReceiverTestTv = (TextView) findViewById(R.id.tv_receive_test);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
        unregisterReceiver(dynamicReceiver);
        unregisterReceiver(broadcastReceiver);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent != null) {
                if (!TextUtils.isEmpty(intent.getAction())) {
                    Toast.makeText(getApplicationContext(), intent.getAction(), Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(intent.getStringExtra("data"))) {
                    Toast.makeText(getApplicationContext(), intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();

                    mReceiverTestTv.setText("" + intent.getStringExtra("data"));
                }
            }
        }

    }


    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub
            String string = intent.getStringExtra("data");
//            Bundle bundle = getResultExtras(false);
//            String setedString = bundle.getString("data");
//            bundle.putString("data", setedString + "");
//            setResultExtras(bundle);
//            mReceiverTestTv.setText("" + setedString);
//            Message msg = new Message();
//            msg.obj = string;
//            handler.sendEmptyMessage(0x123);
//            result = string;
            Log.i("getAction", "intent.getAction" + intent.getAction());
            switch (intent.getAction()) {
                case "test1":
                    mReceiverTestTv.setText("" + string);
                    break;
                case "test2":
                    break;
                case "test3":
                    break;
                case "test4":
                    break;
                default:
                    break;
            }
            Toast.makeText(arg0, "received:" + string, Toast.LENGTH_SHORT).show();
        }
    }

//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            Log.i("", "msg.what" + "+msg.what");
//            if (msg.what == 0x123) {
//                mReceiverTestTv.setText("" + msg.obj);
//            }
//        }
//    };

    public class DynamicReceiver extends BroadcastReceiver {
        private String returnValue1;
        private String returnValue2;
        private String returnValue3;

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "com.android.carpower.intent.standbyentre":
                    returnValue1 = "Receiving broadcast：" + "standbyentre";
                    String string = intent.getStringExtra("data");
                    mReceiverTestTv.setText("" + string);
                    break;
                case "com.android.carpower.intent.standbyexit":
                    returnValue2 = "Receiving broadcast：" + "standbyexit";
                    break;
                case "com.android.carpower.intent.restrictedentre":
                    returnValue3 = "Receiving broadcast：" + "restrictedentre";
                    break;
                default:
                    break;

            }
        }
    }

}
