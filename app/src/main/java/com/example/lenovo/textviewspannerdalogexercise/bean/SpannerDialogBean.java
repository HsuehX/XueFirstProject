package com.example.lenovo.textviewspannerdalogexercise.bean;

/**
 * Created by xueww on 2017/9/12.
 */

public class SpannerDialogBean {
    private String mValue = "";
    private String mText = "";
    private String mNnum = "";

    public SpannerDialogBean() {
        mValue = "";
        mText = "";
    }

    public SpannerDialogBean(String value, String text) {
        mValue = value;
        mText = text;
    }

    public SpannerDialogBean(String value, String text, String num) {
        mValue = value;
        mText = text;
        mNnum = num;
    }

    @Override
    public String toString() {
        return mText;
    }

    public String getValue() {
        return mValue;
    }

    public String getText() {
        return mText;
    }

    public String getmNnum() {
        return mNnum;
    }

    public void setmNnum(String mNnum) {
        this.mNnum = mNnum;
    }
}
