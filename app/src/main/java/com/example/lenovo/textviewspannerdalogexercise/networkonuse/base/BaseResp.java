package com.example.lenovo.textviewspannerdalogexercise.networkonuse.base;


import java.io.Serializable;


public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = -5494927612004202996L;
    /// <summary>
    /// 响应代码
    /// </summary>
    private String code;

    /// <summary>
    /// 反馈信息
    /// </summary>
    private String msg;

    /// <summary>
    /// 返回信息体
    /// </summary>
    private T Task;

    public boolean isStatus() {

        return  code.equals("success")? true : false;
    }

    public void setStatus(String status) {
        this.code = status;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getResult() {
        return Task;
    }

    public void setResult(T result) {
        this.Task = result;
    }
}
