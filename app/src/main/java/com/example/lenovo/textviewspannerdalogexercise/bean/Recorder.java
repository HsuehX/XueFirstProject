package com.example.lenovo.textviewspannerdalogexercise.bean;

/**
 * Created by xue on 2018/4/18.
 * 仿微信语音实体类
 */

public class Recorder {
    float time;
    String filePathString;

    public Recorder(float time, String filePathString) {
        super();
        this.time = time;
        this.filePathString = filePathString;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePathString() {
        return filePathString;
    }

    public void setFilePathString(String filePathString) {
        this.filePathString = filePathString;
    }
}
