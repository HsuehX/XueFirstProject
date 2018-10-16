package com.example.lenovo.textviewspannerdalogexercise.networkonuse.subscriber;

import android.content.Context;

import rx.Subscriber;

public class GenericHttpSubscriber<T> extends Subscriber<T> {

    private final static String TAG = GenericHttpSubscriber.class.getName();
    private Subscriber<T> mSubscriber;
    private Context mContext;

    public GenericHttpSubscriber(Context ctx) {
        mContext = ctx;
    }

    protected Context getContext() {
        return mContext;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
