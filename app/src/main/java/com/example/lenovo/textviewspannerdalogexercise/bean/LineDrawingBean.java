package com.example.lenovo.textviewspannerdalogexercise.bean;

/**
 * Created by xueww on 2017/12/12.
 */

public class LineDrawingBean {
    private String mValue = "";
    private String mText = "";
    private String mTime = "";

    public LineDrawingBean() {
        mValue = "";
        mText = "";
    }

    public LineDrawingBean(String value, String text) {
        mValue = value;
        mText = text;
    }

    public LineDrawingBean(String value, String text, String time) {
        mValue = value;
        mText = text;
        mTime = time;
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

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
