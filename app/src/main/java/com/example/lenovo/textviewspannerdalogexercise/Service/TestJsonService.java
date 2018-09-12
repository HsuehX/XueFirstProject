package com.example.lenovo.textviewspannerdalogexercise.Service;

import android.content.Context;

import com.example.lenovo.textviewspannerdalogexercise.bean.BeanReqPdaFastCollection;

import com.example.lenovo.textviewspannerdalogexercise.bean.BeanRespPdaFastCollection;
import com.example.lenovo.textviewspannerdalogexercise.json.JsonUtils;
import com.example.lenovo.textviewspannerdalogexercise.json.PickupQuickTest;


/**
 * Created by xueww on 2017/11/24.
 */

public class TestJsonService extends Service {
    private Context mContext;
    private final static String TAG = TestJsonService.class.getName();
    private BeanRespPdaFastCollection beanRespPdaFastCollection = new BeanRespPdaFastCollection();

    public TestJsonService(Context ctx) {
        mContext = ctx;
    }

    public BeanRespPdaFastCollection getFastCollectionMail() {
        PickupQuickTest test = new PickupQuickTest();
        beanRespPdaFastCollection = JsonUtils.toClass(test.FIND_PDA_PICK_UP_POST_INFO_QUICK_RESP, BeanRespPdaFastCollection.class);
        return beanRespPdaFastCollection;
    }

}
