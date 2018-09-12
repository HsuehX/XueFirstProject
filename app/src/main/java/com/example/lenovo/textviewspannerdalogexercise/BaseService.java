package com.example.lenovo.textviewspannerdalogexercise;

import android.content.Context;

public class BaseService {

    private Context mContext;

    public BaseService(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }
}
