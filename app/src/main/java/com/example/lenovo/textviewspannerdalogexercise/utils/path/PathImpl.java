//package com.example.lenovo.textviewspannerdalogexercise.utils.path;
//
//import android.content.Context;
//import android.os.Environment;
//import android.text.TextUtils;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.File;
//
//public class PathImpl implements IPath {
//
//    private static final String PATH_UPDATE_APK = "UPDATE_APK";
//
//    private String mUpdateApkPath = "";
//    private Context mContext = null;
//    private static final String PATH_CACHE = "cache";
//    private static final String PATH_SYSTEM = "system";
//    private static final String PATH_LOGS = "logs";
//
//    private String mSDRootPath = "";
//    private String mCachePath = "";
//    private String mLogsPath = "";
//    private String mSystemPath = "";
//
//
//    public PathImpl() {
//    }
//
//    private void createPath(String path) {
//        FileUtils.makeDir(path);
//    }
//
//
//    @Override
//    public String getCachePath() {
//        if (!TextUtils.isEmpty(mCachePath)) {
//            return mCachePath;
//        }
//        mCachePath = PathUtil.getInstance().BASE_PATH + File.separator + PATH_CACHE + File.separator;
//        createPath(mCachePath);
//        return mCachePath;
//    }
//
//    @Override
//    public String getUpdateApkPath() {
//        if (!TextUtils.isEmpty(mUpdateApkPath)) {
//            return mUpdateApkPath;
//        }
//
//        mUpdateApkPath = PathUtil.getInstance().BASE_PATH + File.separator + PATH_UPDATE_APK + File.separator;
//        createPath(mUpdateApkPath);
//        return mUpdateApkPath;
//    }
//
//    @Override
//    public String getSystemPath() {
//        if (StringUtils.isNotEmpty(mSystemPath)) {
//            return mSystemPath;
//        }
//        mSystemPath = getSDRootPath() + File.separator + PATH_SYSTEM + File.separator;
//        createPath(mSystemPath);
//        return mSystemPath;
//    }
//
//    @Override
//    public String getLogPath() {
//        if (StringUtils.isNotEmpty(mLogsPath)) {
//            return mLogsPath;
//        }
//        mLogsPath = getSDRootPath() + File.separator + PATH_LOGS + File.separator;
//        createPath(mLogsPath);
//        return mLogsPath;
//    }
//
//    @Override
//    public String getSDRootPath() {
////        String packageName = mContext.getPackageName();
////        if (StringUtils.isNotEmpty(mSDRootPath)) {
////            return mSDRootPath;
////        }
////        if (mContext == null) {
////            return "";
////        }
////        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
////        if (!sdCardExist) {
////            mSDRootPath = mContext.getFilesDir().getAbsolutePath();
////            createPath(mSDRootPath);
////            System.out.println("### use inner sd path");
////            return mSDRootPath;
////        }
////        mSDRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + packageName;
////        createPath(mSDRootPath);
////        System.out.println("### use outter sd path");
//        return mSDRootPath;
//    }
//
//
//}
