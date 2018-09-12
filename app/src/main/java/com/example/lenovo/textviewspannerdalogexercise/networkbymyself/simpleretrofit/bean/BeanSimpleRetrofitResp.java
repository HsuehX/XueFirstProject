package com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xue on 2018/8/27.
 */

public class BeanSimpleRetrofitResp implements Serializable {

    @SerializedName("results")
    private List<BeanSimpleRetrofit> mResults;

    public List<BeanSimpleRetrofit> getmResults() {
        return mResults;
    }

    public void setmResults(List<BeanSimpleRetrofit> mResults) {
        this.mResults = mResults;
    }
}
