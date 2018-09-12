//package com.example.lenovo.textviewspannerdalogexercise.utils.path;
//
//import android.text.TextUtils;
//import java.io.File;
//
///**
// */
//public final class FileUtils {
//
//    /**
//     * 默认构造方法
//     */
//    private FileUtils() {
//    }
//
//    /**
//     * 检测路径是否存在，不存在则创建
//     *
//     * @param path 路径
//     */
//    public static void makeDir(final String path) {
//        if (!TextUtils.isEmpty(path)) {
//            File file = new File(path);
//            if (!file.isDirectory()) { // 判断文件夹是否存在
//                file.mkdirs(); // 创建文件夹包括创建必需但不存在的父目录
//            }
//        }
//    }
//}