//package com.example.lenovo.textviewspannerdalogexercise.utils.path;
//
//import android.content.Context;
//import android.os.Environment;
//
//import java.io.File;
//
///**
// * Created by xue on 2018/1/15.
// * 路径工具 使用单例模式供其他类调用 接口使用路径工具模板类IPath
// */
//
//public class PathUtil implements IPath {
//    private static PathUtil instance;
//    private IPath mPathImpl;
//
//    private PathUtil() {
//
//    }
//
//    public static final String SD_PATH = Environment.getExternalStorageDirectory() + File.separator;//内部存储，（File.separator夸平台）
//    public String BASE_PATH = SD_PATH;
//
//    public static PathUtil getInstance() {
//        if (instance == null) {
//            instance = new PathUtil();
//        }
//        return instance;
//    }
//
//    public void init(Context mContext) {
//        mPathImpl = new PathImpl();
//        BASE_PATH = SD_PATH + mContext.getPackageName();
//    }
//
//    @Override
//    public String getCachePath() {
//        if (mPathImpl == null) {
//            return "";
//        }
//        return mPathImpl.getCachePath();
//    }
//
//    @Override
//    public String getUpdateApkPath() {
//        if (mPathImpl == null) {
//            return "";
//        }
//        return mPathImpl.getUpdateApkPath();
//    }
//
//    @Override
//    public String getSystemPath() {
//        if (mPathImpl == null) {
//            return "";
//        }
//        return mPathImpl.getSystemPath();
//    }
//
//    @Override
//    public String getLogPath() {
//        if (mPathImpl == null) {
//            return "";
//        }
//        return mPathImpl.getLogPath();
//    }
//
//    @Override
//    public String getSDRootPath() {
//        if (mPathImpl == null) {
//            return "";
//        }
//        return mPathImpl.getSDRootPath();
//    }
//
//
//    /**
//     * 数据库路径
//     *
//     * @return
//     */
//    public String getDbPath() {
//        return BASE_PATH + "/db/";
//    }
//}
