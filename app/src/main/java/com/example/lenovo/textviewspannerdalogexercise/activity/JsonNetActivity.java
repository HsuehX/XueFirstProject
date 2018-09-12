package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.AsyncListUtil;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.Service.TestJsonService;
import com.example.lenovo.textviewspannerdalogexercise.bean.BeanReqPdaFastCollection;
import com.example.lenovo.textviewspannerdalogexercise.bean.BeanRespPdaFastCollection;
import com.example.lenovo.textviewspannerdalogexercise.json.JsonUtils;
import com.example.lenovo.textviewspannerdalogexercise.json.PickupQuickTest;

import java.text.DecimalFormat;

import butterknife.Bind;

/**
 * Created by xueww on 2017/11/24.
 */

public class JsonNetActivity extends Activity {

    @Bind(R.id.one_tv)
    public TextView mOneTv;
    @Bind(R.id.two_tv)
    public TextView mTwoTv;

    private TestJsonService mTestJsonService;
    private BeanRespPdaFastCollection mBeanRespPdaFastCollection = new BeanRespPdaFastCollection();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_net);
        getMail();
    }

    private void getMail() {
        mTestJsonService = new TestJsonService(this);
        mBeanRespPdaFastCollection = mTestJsonService.getFastCollectionMail();
        mOneTv.setText(mBeanRespPdaFastCollection.getReceiverAddr());
        mTwoTv.setText(mBeanRespPdaFastCollection.getExportGridName());
    }
}
