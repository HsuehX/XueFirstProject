package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.testphone.CallingStateListener;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xue on 2019/2/10.
 */

public class AsynchronousLockActivity extends Activity implements CallingStateListener.OnCallStateChangedListener {
    private static final String TAG = "AsynchronousLockActivity.java";
    private TextView mStateIdleTv;
    private TextView mStateOffhookTv;
    private TextView mStateRingingTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynchronous_lock);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        destroyThread();
//        stop();
        if (mCallingStateListener != null) {
            mCallingStateListener.stopListener();
        }
        mHandler.removeCallbacks(mRunnable);
    }

    private int count = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        public void run() {
            //为了方便 查看，我们用Log打印出来
            Log.e(TAG, Thread.currentThread().getName() + " " + count);
            count++;
            if (count == 10) {
                TestThead testThead = new TestThead();
                testThead.start();
            } else if (count == 100) {
                TestThead testThead = new TestThead();
                testThead.start();
            }
//            setTitle("" + count);
            //每2秒执行一次
            mHandler.postDelayed(mRunnable, 10);
        }
    };

    private class TestThead extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                try {
                    sleep(1);
                    Log.i(TAG, "test Thread ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

//    ReceiveThead receiveThead;
//    boolean mWorking = false;
//
//    private class ReceiveThead extends Thread {
//        @Override
//        public void run() {
//            int i = 0;
//            while (i < 1) {
//                try {
//                    sleep(1);
////                    if (receiverState != lastState) {
////                        judgePowerState(receiverState);
////                    }
//                    Log.i(TAG, "test");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void start() {
//        mWorking = true;
//        if (receiveThead != null && receiveThead.isAlive()) {
//            Log.i(TAG, "start: thread is alive");
//        } else {
//            receiveThead = new ReceiveThead();
//            receiveThead.start();
//        }
//    }
//
//    public void stop() {
//        if (mWorking) {
//            if (receiveThead != null && receiveThead.isAlive()) {
//                int i = -1;
//                while (i < 0) {
//                    receiveThead.interrupt();
//                    receiveThead = null;
//                }
//            }
//            mWorking = false;
//        }
//    }
//
//    private void destroyThread() {
//        try {
//            if (null != receiveThead && Thread.State.RUNNABLE == receiveThead.getState()) {
//                try {
//                    Thread.sleep(1000);
//                    receiveThead.interrupt();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            receiveThead = null;
//        }
//    }

    CallingStateListener mCallingStateListener;

    private void init() {
        initView();
//        TestAsychronousLock();
        testPhoneState();
//        test();

        mCallingStateListener = new CallingStateListener(this);
        mCallingStateListener.setOnCallStateChangedListener(this);
        mCallingStateListener.startListener();
//        start();
        mHandler.post(mRunnable);
    }


    private void initView() {
        mStateIdleTv = (TextView) findViewById(R.id.tv_call_state_idle);
        mStateOffhookTv = (TextView) findViewById(R.id.tv_call_state_offhook);
        mStateRingingTv = (TextView) findViewById(R.id.tv_call_state_ringing);
    }


    @Override
    public void onCallStateChanged(int state, String number) {
        String ss = mStateIdleTv.getText() + "" + state + "";
        mStateIdleTv.setText(ss);
        mStateOffhookTv.setText(number);
        Log.e(TAG, "" + state + number);
    }

    static Lock lock = new ReentrantLock();

    private void TestAsychronousLock() {
        MyThread t1 = new MyThread("窗口一");
        MyThread t2 = new MyThread("窗口二");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        private static int count = 10;

        public MyThread(String name) {
            super(name);
        }

        public static void increase() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "售出：" + (count--) + " 票");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        @Override
        public void run() {
            while (count > 0) {
                //静态代码块锁，定义同一个对象
                increase();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void testPhoneState() {

        PhoneStateListener listener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE: /* 无任何状态时 */
                        mStateIdleTv.setText("无任何状态时");
                        Log.e("TAG", "无任何状态时");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK: /* 接起电话时 */
                        mStateRingingTv.setText("接起电话时");
                        Log.e("TAG", "接起电话时");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING: /* 电话进来时 */
                        mStateRingingTv.setText("电话进来时");
                        Log.e("TAG", "电话进来时");
                        break;
                    default:
                        break;
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        // 取得电话服务
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //监听电话的状态
        telManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

//    Parcelable

    private void test() {
        PhoneStateListener listener = new PhoneStateListener() {

            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                Log.d(TAG, "CustomPhoneStateListener state: "
                        + state + " incomingNumber: " + incomingNumber);
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:      // 电话挂断
                        mStateIdleTv.setText("无任何状态时");
                        Log.e(TAG, "CALL_STATE_IDLE");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:   // 电话响铃
                        Log.e(TAG, "CALL_STATE_RINGING");
                        mStateRingingTv.setText("接起电话时");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:   // 来电接通 或者 去电，去电接通  但是没法区分
                        mStateRingingTv.setText("电话进来时");
                        Log.e(TAG, "CALL_STATE_OFFHOOK");
                        break;
                }
            }
        };

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

}
