package com.example.lenovo.textviewspannerdalogexercise.view.whellviewda.bean;

/**
 * Created by xue on 2018/8/2.
 */

public class ChildBean {
    private String parentValue;
    private String childVaule;

    public ChildBean(String parentValue, String childVaule) {
        this.parentValue = parentValue;
        this.childVaule = childVaule;
    }


    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue;
    }

    public String getChildVaule() {
        return childVaule;
    }

    public void setChildVaule(String childVaule) {
        this.childVaule = childVaule;
    }
}
