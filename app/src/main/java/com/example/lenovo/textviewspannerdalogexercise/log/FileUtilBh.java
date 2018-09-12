/**
 * @Title: FileUtil.java
 * @Package com.bohan.ems.utils
 * @Description: TODO
 * @author A18ccms A18ccms_gmail_com
 * @date 2017年2月22日 下午3:34:00
 * @version V1.0
 */
package com.example.lenovo.textviewspannerdalogexercise.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;


import com.example.lenovo.textviewspannerdalogexercise.view.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: FileUtil
 *	文件工具类，用于应用的文件操作：读、写、删除
 * @Description: TODO
 *
 * @author walker
 *
 * @date 2017年2月22日 下午3:34:00
 *
 */
public class FileUtilBh {
    private static String logPath;
    /** 日志记录标记：为true则记录日志，为false不记录日志，程序启动默认为false */
    private static boolean isRecord = false;
    private static int effectTime = 1;

    /**
     * Add by walker Date 2017年2月23日
     *
     * @Description: TODO 设置日志记录标记
     * @param isRecords
     *            设置当前日志记录标记
     */
    public static void setLogFlag(boolean isRecords) {
        isRecord = isRecords;
    }

    /**
     * Add by walker Date 2017年2月23日
     *
     * @Description: TODO 获取记录标记
     * @return 返回当前是否记录日志：返回true为记录日志状态；返回false非日志状态
     */
    public static boolean getRecordFlag() {
        return isRecord;
    }

    public static void init() {
        logPath = PathUtilBh.getInstance().getLogPath();
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (fileMap == null) {
            fileMap = new HashMap<String, File>();
        }
    }

    public static void deleteDirectory(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (!dir.isDirectory()) {
            dir.delete();
            return;
        }
        File[] listFile = dir.listFiles();
        for (File file : listFile) {
            if (file.exists()) {
                if (!file.delete()) {
                    //删除失败
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    }
                }
            }
        }
    }

    /**
     * Add by walker Date 2017年2月20日
     *
     * @param <T>
     * @Description: TODO
     */
    public static <T> void writeLog(List<T> list) {
        if (list == null) {
            return;
        }
        for (Object obj : list) {
            writeLog(obj + "");
        }
    }

    /**
     * Add by walker Date 2017年2月20日
     *
     * @param <T>
     * @Description: TODO
     */
    public static <T> void writeLog(T[] res) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append("[" + i + "]:" + res[i] + "\t");
        }
        writeLog(sb.toString());
    }

    /**
     * Add by walker Date 2017年2月23日
     *
     * @Description: TODO 写文件日志信息
     * @param msg
     *            写入日志信息
     */
    public static void writeLog(String msg) {
        writeLog(StringUtils.getCurrentDate() + "\n" + msg, "log");
    }

    /**
     * Add by walker Date 2017年2月23日
     *
     * @Description: TODO 写文件日志信息
     * @param flag 0-获取实体中所有的字段; 其他为有值字段
     */
    public static void writeLog(Object obj, int flag) {
        writeLog(getJsonByObj(obj, flag), "log");
    }

    /**
     * Add by walker Date 2017年2月7日
     * @Description: TODO
     * 	获取对象对应的JSON数据
     *  @param object
     *  @param flag 0-获取实体中所有的字段; 其他为有值字段
     *  @return 返回对象的json字符串
     */
    public static String getJsonByObj(Object object, int flag) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        sb.append("{");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (flag == 0) {
                    sb.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
                } else if (!StringUtils.isEmptyUnNull("" + field.get(object))) {
                    sb.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Add by walker Date 2017年2月23日
     *
     * @Description: TODO 写文件日志信息
     * @param msg
     *            写入日志信息
     * @param fileName
     *            日志文件名称
     */
    public static void writeLog(String msg, String fileName) {
        // 当前为非日志状态，不记录日志信息
        if (!isRecord) {
            return;
        }
        writeMsg(msg, fileName);
    }

    static HashMap<String, File> fileMap;

    /**
     * Add by walker Date 2017年3月12日
     * @Description: TODO
     * 	向指定文件中输出信息
     *  @param msg 写入内容信息
     *  @param fileName 写入文件名称
     */
    public static void writeMsg(String msg, String fileName) {
        try {
            init();
            String path = logPath + fileName.toUpperCase() + StringUtils.getCurrentDay() + ".txt";
            FileWriter fw;
            File file = fileMap.get(path);
            if (file == null) {
                file = new File(path);
                fileMap.put(path, file);
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, true);
            fw.write(StringUtils.getCurrentDate() + "\n" + msg + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add by walker Date 2017年3月12日
     * @Description: TODO
     *	清空无效日志信息:最后修改日期距现在时间差（天数）大于有效日期的文件被删除
     */
    public static void deleteHistory() {
        try {
            init();
            File file = new File(logPath);
            if (file.isDirectory() && !file.exists()) {
                file.mkdirs();
                writeError("创建LOG日志路径");
                return;
            }
            for (File fileChild : file.listFiles()) {
                int difData = TypeUtil.strToInt(StringUtils.getDay(System.currentTimeMillis() - fileChild.lastModified()));
                if (difData > effectTime) {
                    writeError("删除日志：" + fileChild.getName() + "\t占用时间：" + StringUtils.getDay(System.currentTimeMillis() - fileChild.lastModified()) + "\t生效时间：" + effectTime);
                    fileChild.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add by walker Date 2017年3月8日
     * @Description: TODO
     * 写error日志，无标记限制
     *  @param msg 写入文本内容
     */
    public static void writeError(String msg) {
        writeMsg(msg, "error");
    }

    /**
     * Add by walker Date 2017年3月8日
     * @Description: TODO
     * 写error日志，无标记限制
     *  @param msg 写入文本内容
     */
    public static void writeError(String msg, Throwable e) {
        String errorMsg = "\nErrorMsg:";
        if (e != null) {
            errorMsg += "Throwable------>\t" + e + "\nMessage\t" + e.getMessage();
        }
        writeMsg(msg + errorMsg, "error");
    }

    public static final String XML_ENTER_SIGN = "\n";
    public static final String XML_ENCODING = "UTF-8";
    public static final String MD5_ENCODING = "UTF-8";
    public static final String DATA_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void saveLog(String log) {
        if (!TextUtils.isEmpty(log)) {
            // 判断手机是否有SD卡
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                final Date date = new Date();
                SimpleDateFormat df = null;
                String returnValue = "";
                df = new SimpleDateFormat(DATA_PATTERN);
                final String name = "log" + df.format(date) + ".txt";//命名方式：log+当前日期+.txt
                df = new SimpleDateFormat(TIME_PATTERN);
                log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
                saveFile(PathUtilBh.getInstance().getLogPath(), name, log, true);
            }
        }
    }

    public static void saveNetLog(String log) {
        if (!TextUtils.isEmpty(log)) {
            // 判断手机是否有SD卡
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                final Date date = new Date();
                SimpleDateFormat df = null;
                String returnValue = "";
                df = new SimpleDateFormat(DATA_PATTERN);
                final String name = "NETlog" + df.format(date) + ".txt";
                df = new SimpleDateFormat(TIME_PATTERN);
                log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
                saveFile(PathUtilBh.getInstance().getNetLogPath(), name, log, true);
            }
        }
    }

    public static void saveRfLog(String log) {
        if (!TextUtils.isEmpty(log)) {
            // 判断手机是否有SD卡
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                final Date date = new Date();
                SimpleDateFormat df = null;
                String returnValue = "";
                df = new SimpleDateFormat(DATA_PATTERN);
                final String name = "RFlog" + df.format(date) + ".txt";
                df = new SimpleDateFormat(TIME_PATTERN);
                log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
                saveFile(PathUtilBh.getInstance().getRfPointLogPath(), name, log, true);
            }
        }
    }

//	public static void saveG20Log(String log){
//		if (!TextUtils.isEmpty(log)) {
//			// 判断手机是否有SD卡
//			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//				final Date date = new Date();
//				SimpleDateFormat df = null;
//				String returnValue = "";
//				df = new SimpleDateFormat(DATA_PATTERN);
//				final String name = "g20_log" + df.format(date) + ".txt";
//				df = new SimpleDateFormat(TIME_PATTERN);
//				log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
//				saveFile(PathUtil.getInstance().getG20Path(), name, log, true);
//			}
//		}
//	}

    /**
     * 检测路径是否存在，不存在则创建
     *
     * @param path
     *            路径
     */
    private static final String Tag = "FileUtil";

    public static void makeDir(final String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (!file.isDirectory()) { // 判断文件夹是否存在
                file.mkdirs(); // 创建文件夹包括创建必需但不存在的父目录
            }
        } else {
            Log.e(Tag, " path:" + path + ",create path error!");
            FileUtilBh.saveLog("创建文件夹失败:" + path);
        }
    }

    public static synchronized void saveFile(final String filePath, final String fileName, final String str, final boolean append) {
        if (!TextUtils.isEmpty(filePath) && !TextUtils.isEmpty(fileName) && !TextUtils.isEmpty(str)) {
            // 判断手机是否有SD卡
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                FileOutputStream fos = null;
                OutputStreamWriter out = null;
                try {
                    makeDir(filePath);
                    final File file = new File(filePath, fileName);
                    fos = new FileOutputStream(file, append);
                    out = new OutputStreamWriter(fos, XML_ENCODING);
                    out.write(str);
                    out.close();
                } catch (final Exception e) {
                    e.printStackTrace();
                    Log.e(Tag, "Save log to file error !" + e.getMessage());
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                            out = null;
                        }
                        if (fos != null) {
                            fos.close();
                            fos = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 只用于存储异常日志信息
     *
     * @param log
     */
    public static void saveErrorLog(String log) {
        if (!TextUtils.isEmpty(log)) {
            // 判断手机是否有SD卡
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                final Date date = new Date();
                SimpleDateFormat df = null;
                String returnValue = "";
                df = new SimpleDateFormat(DATA_PATTERN);
                final String name = "log" + df.format(date) + ".txt";
                df = new SimpleDateFormat(TIME_PATTERN);
                log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
                saveFile(PathUtilBh.getInstance().getLogPath(), name, log, true);
            }
        }
    }


    /**
     * 判断文件是否存在
     *
     * @param path 文件路径（带扩展名）
     * @return 是否存在
     */
    public static boolean havaFile(final String path) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            return file.exists();
        } else {
            Log.e(Tag, "path: " + path + " ,Can not determine if a file exists!");
            FileUtilBh.saveLog("无法判断附件是否存在:" + path);
            return false;
        }
    }
}
