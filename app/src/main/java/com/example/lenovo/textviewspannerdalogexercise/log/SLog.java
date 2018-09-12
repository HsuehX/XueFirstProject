package com.example.lenovo.textviewspannerdalogexercise.log;

import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;


/**
 */
public class SLog {

	private static final String Tag = "SLog";

	public static final int LOGGER_TYPE_LOG = 0;
	public static final int LOGGER_TYPE_SAVE = 1;
	public static final int LOGGER_TYPE_ALL = 2;
	public static final int LOGGER_TYPE_NONE = 3;

	public static int LOGGER_TYPE = LOGGER_TYPE_ALL;
	private static int MAX_LOGIN_FILE_COUNT = 5;

	/**
	     */
	public static void Console(String content) {
		if (TextUtils.isEmpty(content) || LOGGER_TYPE == LOGGER_TYPE_NONE) {
			return;
		}
		if (LOGGER_TYPE == LOGGER_TYPE_LOG) {
			Log.i(Tag, content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_SAVE) {
			FileUtilBh.saveLog(content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_ALL) {
			Log.i(Tag, content);
			FileUtilBh.saveLog(content);
		}
	}

	public static void Console(String tag,String content) {
		if(tag!=null){
			Console(tag+" "+content);
		}else{
			Console(content);
		}

	}

	public static void netLog(String content){
		if (TextUtils.isEmpty(content) || LOGGER_TYPE == LOGGER_TYPE_NONE) {
			return;
		}
		if (LOGGER_TYPE == LOGGER_TYPE_LOG) {
			Log.i(Tag, content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_SAVE) {
			FileUtilBh.saveNetLog(content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_ALL) {
			Log.i(Tag, content);
			FileUtilBh.saveNetLog(content);
		}
	}

	public static void rfLog(String content){
		if (TextUtils.isEmpty(content) || LOGGER_TYPE == LOGGER_TYPE_NONE) {
			return;
		}
		if (LOGGER_TYPE == LOGGER_TYPE_LOG) {
			Log.i(Tag, content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_SAVE) {
			FileUtilBh.saveRfLog(content);
		} else if (LOGGER_TYPE == LOGGER_TYPE_ALL) {
			Log.i(Tag, content);
			FileUtilBh.saveRfLog(content);
		}
	}


	public static void cleanOutOfDateLog() {
		final String dir = PathUtilBh.getInstance().getNetLogPath(); // 日志文件夹
		File tempDir = new File(dir);
		if (tempDir != null && tempDir.exists()) {
			File[] fileList = tempDir.listFiles();
			int fileNum = fileList.length;
			TreeMap<Long, File> tm = new TreeMap<Long, File>();
			for (int i = 0; i < fileNum; i++) {
				Long tempLong = fileList[i].lastModified();
				if (tm.containsKey(tempLong)) {
					tm.put(tempLong + i + 1, fileList[i]);
				} else {
					tm.put(tempLong, fileList[i]);
				}
			}
			List<File> tempDeleteFiles = new ArrayList<File>();
			for (int i = 0; i < fileNum; i++) {
				if (i >= MAX_LOGIN_FILE_COUNT) {
					File tempFile = tm.get(tm.lastKey());
					if (tempFile != null) {
						tempDeleteFiles.add(tempFile);
					}
					if (tm.containsKey(tm.lastKey())) {
						tm.remove(tm.lastKey());
					}
				} else {
					if (tm.containsKey(tm.lastKey())) {
						tm.remove(tm.lastKey());
					}
				}
			}
			for (File tempFile : tempDeleteFiles) {
				tempFile.delete();
			}
		}
	}

	public static String saveException(final Throwable e) {

		// new Thread(){
		// @Override
		// public void run() {
		// Looper.prepare();
		// String errorMsg=MessageUtils.getExcptionToastMessage(e);
		// Toast.makeText(VissionApplication.getInstance(),
		// "debug：程序出现【"+errorMsg+"】.",
		// Toast.LENGTH_LONG).show();
		// Looper.loop();
		// }
		// }.start();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
//		SLog.Console(sw.toString());
		e.printStackTrace();

		return sw.toString();
	}

//	/**
//	 * 检查大于5天的日志文件删除掉
//	 */
//	public static void checkFileNeedDel() {
//		String cur_data = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		File delFile = new File(AudioRecordButton.mPath + "/");
//
//		if (!delFile.exists()) {
//			return;
//		}
//
//		File[] files = delFile.listFiles();// 取得log下所有文件夹
//		for (File file : files) {
//			try {
//				String old_data = file.getName();
//				if (getTwoDay(cur_data, old_data) >= MAX_LOGIN_FILE_COUNT) {// 日志文件保存5天，其他删除
//					RecursionDeleteFile(new File(AudioRecordButton.mPath + "/"
//							+ old_data));
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	private static int getTwoDay(String curdata, String olddata) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
		int day = 0;
		try {
			Date date = myFormatter.parse(curdata);
			Date mydate = myFormatter.parse(olddata);
			day = (int) (date.getTime() - mydate.getTime())
					/ (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return day;
	}

	public static void RecursionDeleteFile(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return;
			}
			for (File f : childFile) {
				RecursionDeleteFile(f);
			}
			file.delete();
		}
	}

}
