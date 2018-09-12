package com.example.lenovo.textviewspannerdalogexercise.log;

import android.text.TextUtils;


import java.io.File;

public class PathImplBh implements IPathBh {

	private static final String PATH_CACHE = "cache";
	private static final String PATH_LOG = "slog";
	private static final String PATH_NETLOG = "Netlog";
	private static final String PATH_RFLOG = "Rflog";
	private String mCachePath = "";
	private String mLogPath = "";
	private String mNetLogPath = "";
	private String mRfLogPath = "";
	public PathImplBh() {
	}

	private void createPath(String path) {
		FileUtilBh.makeDir(path);
	}


	@Override
	public String getCachePath() {
		if (!TextUtils.isEmpty(mCachePath)) {
			return mCachePath;
		}
		mCachePath = PathUtilBh.getInstance().BASE_PATH + File.separator + PATH_CACHE + File.separator;
		createPath(mCachePath);
		return mCachePath;
	}

	@Override
	public String getLogPath() {
		if (!TextUtils.isEmpty(mLogPath)) {
			return mLogPath;
		}
		mLogPath = PathUtilBh.getInstance().BASE_PATH + File.separator + PATH_LOG + File.separator;
		createPath(mLogPath);
		return mLogPath;
	}

	@Override
	public String getNetLogPath() {
		if (!TextUtils.isEmpty(mNetLogPath)) {
			return mNetLogPath;
		}
		mNetLogPath = PathUtilBh.getInstance().BASE_PATH + File.separator + PATH_NETLOG + File.separator;
		createPath(mNetLogPath);
		return mNetLogPath;
	}
	@Override
	public String getRfPointLogPath() {
		if (!TextUtils.isEmpty(mRfLogPath)) {
			return mRfLogPath;
		}
		mRfLogPath = PathUtilBh.getInstance().BASE_PATH + File.separator + PATH_RFLOG + File.separator;
		createPath(mRfLogPath);
		return mRfLogPath;
	}
}
