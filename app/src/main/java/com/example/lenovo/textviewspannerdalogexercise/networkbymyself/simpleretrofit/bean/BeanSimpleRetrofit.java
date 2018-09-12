package com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xue on 2018/8/27.
 */

public class BeanSimpleRetrofit implements Serializable {
    @SerializedName("desc")
    private String mDesc;

    @SerializedName("images")
    private List<String> mImages;

    @SerializedName("publishedAt")
    private Date mPublishedAt;

    @SerializedName("url")
    private String mUrl;

    public String getDesc() {
        return mDesc;
    }

    public List<String> getImages() {
        return mImages;
    }

    public Date getPublishedAt() {
        return mPublishedAt;
    }

    public String getUrl() {
        return mUrl;
    }
}
