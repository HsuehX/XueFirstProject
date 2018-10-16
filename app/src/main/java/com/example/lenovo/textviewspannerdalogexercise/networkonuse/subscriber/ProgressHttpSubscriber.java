package com.example.lenovo.textviewspannerdalogexercise.networkonuse.subscriber;

import android.content.Context;

import com.example.lenovo.textviewspannerdalogexercise.view.WaitingDialog;


/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressHttpSubscriber<T> extends GenericHttpSubscriber<T> {

    private final static String TAG = ProgressHttpSubscriber.class.getName();

    private WaitingDialog mWaitingDialog;

    public ProgressHttpSubscriber(Context ctx) {
        super(ctx);
    }

    public ProgressHttpSubscriber(Context ctx, String tipText) {
        super(ctx);
        mWaitingDialog = new WaitingDialog(getContext());
        mWaitingDialog.setMessage(tipText);
    }

    private void showProgressDialog() {
        try {
            if (mWaitingDialog == null) {
                mWaitingDialog = new WaitingDialog(getContext());
                mWaitingDialog.setCanceledOnTouchOutside(false);
                mWaitingDialog.setCancelable(false);
            }else{
                mWaitingDialog.dismiss();
            }
            mWaitingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissProgressDialog() {
        try {
            if (mWaitingDialog != null) {
                mWaitingDialog.dismiss();
                mWaitingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }
}