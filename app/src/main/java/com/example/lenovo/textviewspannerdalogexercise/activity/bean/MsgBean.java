package com.example.lenovo.textviewspannerdalogexercise.activity.bean;

import java.util.Date;

/**
 * Created by xue on 2018/7/28.
 */

public class MsgBean {
    private String msg;
    private Type type;
    private Date date;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public enum Type {
        INCOMING, OUTCOMING

    }

}
