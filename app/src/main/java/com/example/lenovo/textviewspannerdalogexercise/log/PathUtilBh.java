package com.example.lenovo.textviewspannerdalogexercise.log;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 路径工具 使用单例模式供其他类调用 接口使用路径工具模板类IPath
 */
public class PathUtilBh implements IPathBh {

    private static PathUtilBh instance;
    private IPathBh mPathImpl;

    private PathUtilBh() {
    }

    // cachePath = context.getFilesDir().getAbsolutePath();
    public static final String SD_PATH = Environment.getExternalStorageDirectory() + File.separator;
    public String BASE_PATH = SD_PATH;

    public static PathUtilBh getInstance() {
        if (instance == null) {
            instance = new PathUtilBh();
        }
        return instance;
    }

    public void init(Context mContext) {
        mPathImpl = new PathImplBh();
        BASE_PATH = SD_PATH + mContext.getPackageName();
    }

    @Override
    public String getCachePath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getCachePath();
    }

    @Override
    public String getLogPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getLogPath();
    }

    @Override
    public String getNetLogPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getNetLogPath();
    }

    @Override
    public String getRfPointLogPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getRfPointLogPath();
    }

    public String getDbPath() {
        return BASE_PATH + "/db/";
    }
}
