package com.example.lenovo.textviewspannerdalogexercise.networkonuse.base;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class CustomHttpClient {

    private static final String TAG = CustomHttpClient.class.getName();

    public static OkHttpClient create(final int timeout, final boolean useCustomHeader) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
//        builder.addInterceptor(new ReadCookiesInterceptor(App.getInstance()));
//        builder.addInterceptor(new SaveCookiesInterceptor(App.getInstance(),"ch"));
        builder.retryOnConnectionFailure(false);
//        builder.addInterceptor(new GzipRequestInterceptor());
//        builder.addInterceptor(new TokenIntercepter());
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        builder.writeTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        return builder.build();
    }


}
