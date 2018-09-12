package com.example.lenovo.textviewspannerdalogexercise.network;

/**
 * Created by xue on 2018/5/2.
 */

public interface HttCallbaackListener {
    void onSuccess(String response);

    void onError(Exception e);
}
