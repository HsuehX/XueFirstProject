package com.example.lenovo.textviewspannerdalogexercise.fastjson;

/**
 * Created by xue on 2018/4/28.
 */

public class FastJsonBean {
    private int status;
    private String msg, data;

    @Override
    public String toString() {
        return " status=" + status + ", msg=" + msg + ", data=" + data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
