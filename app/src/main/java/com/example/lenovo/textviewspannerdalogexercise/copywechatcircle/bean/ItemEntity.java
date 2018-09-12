package com.example.lenovo.textviewspannerdalogexercise.copywechatcircle.bean;

import java.util.ArrayList;

/**
 * Created by xue on 2018/8/29.
 */

public class ItemEntity {
    private String avatar; // 用户头像URL
    private String title; // 标题
    private String content; // 内容
    private ArrayList<String> imageUrls; // 九宫格图片的URL集合

    public ItemEntity(String avatar, String title, String content, ArrayList<String> imageUrls) {
        super();
        this.avatar = avatar;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public String toString() {
        return "ItemEntity [avatar=" + avatar + ", title=" + title + ", content=" + content + ", imageUrls=" + imageUrls + "]";
    }

}
