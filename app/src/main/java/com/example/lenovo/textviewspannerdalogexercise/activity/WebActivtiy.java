package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.AmountView;

/**
 * Created by xueww on 2017/12/8.
 */

public class WebActivtiy extends Activity {
    private String TAG = "MainActivity";
    private WebView mWebView;
    private AmountView mAmountView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = (WebView) findViewById(R.id.WebView);

        init();

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setGeolocationEnabled(true);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//设置硬件加速
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //开浏览器 localStorage 存储用的
        webSettings.setDomStorageEnabled(true);

        webSettings.setSupportZoom(false);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }


//        mWebView.loadUrl("http://192.168.1.104:3000/index.html");
        mWebView.loadUrl("http://192.168.1.112:8080/index.html");
    }

    private void init() {
        mAmountView = (AmountView) findViewById(R.id.amount_view);
        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickButton(View v) {
        String str = "你好";
        mWebView.loadUrl("javascript:js('" + str + "')");
    }


    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            /**
             * 规则是所有的指令都是以dmr:开头；
             * dmr:showcamera  ->展示摄像头
             * dmr:getiphone   ->拿到电话号码
             *
             *
             */
            if (url.startsWith("dmr:")) {
                if ("showcamera".equals(url.substring(4))) {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                    return true;
                } else if ("getiphone".equals(url.substring(4))) {

                    mWebView.loadUrl("javascript:callback(15755170689)");
                    return true;
                }
            }
            //Log.d(TAG, "shouldOverrideUrlLoading: "+url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


            return super.shouldOverrideUrlLoading(view, request);

        }
    }
}
