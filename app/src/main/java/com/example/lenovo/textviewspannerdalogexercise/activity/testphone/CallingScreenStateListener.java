package com.example.lenovo.textviewspannerdalogexercise.activity.testphone;

import android.content.Context;
import android.util.Log;

/**
 * Created by xue on 2019/2/12.
 */

public class CallingScreenStateListener implements CallingStateListener.OnCallStateChangedListener {

    private static final String TAG = "CallingScreenStateListener";

    private Context mContext = null;
    //监听回调
    private CallingStateListener mCallingStateListener = null;

    public CallingScreenStateListener(Context context) {
        this.mContext = context;
        mCallingStateListener = new CallingStateListener(context);
        mCallingStateListener.setOnCallStateChangedListener(this);
        mCallingStateListener.startListener();
    }

    public void stopListener() {
        mCallingStateListener.stopListener();
//        mScreenObserver.shutdownObserver();
    }


    /********************************** 监听电话 回调 ****************************************/

    /**
     * @param state  监听状态
     * @param number 手机号
     */
    @Override
    public void onCallStateChanged(int state, final String number) {
        switch (state) {
            case CallingStateListener.OnCallStateChangedListener.STATE_RINGING:
                Log.e(TAG, "当前状态为响铃，来电号码：" + number);
                //对应的数据交互方法
                break;
            case CallingStateListener.OnCallStateChangedListener.STATE_IN:
                Log.e(TAG, "当前状态为接听");
                //对应的数据交互方法
                break;
            case CallingStateListener.OnCallStateChangedListener.STATE_OUT:
                Log.e(TAG, "当前状态为拨打，拨打号码：" + number);
                //对应的数据交互方法
                break;
            case CallingStateListener.OnCallStateChangedListener.STATE_IDLE:
                Log.e(TAG, "当前状态为挂断");
                //对应的数据交互方法
                break;
        }
    }
}