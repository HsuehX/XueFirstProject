//package com.example.lenovo.textviewspannerdalogexercise.utils;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Environment;
//import android.util.Log;
//
//import com.example.lenovo.textviewspannerdalogexercise.utils.path.PathUtil;
//
///**
// */
//public final class FileUtil {
//
//    /**
//     * 系统默认的文件路径头
//     */
//    public static final String BASE_FILE_PATH = "file://";
//    // 切记，不支持office2007格式的文件
//    // application/msword
//    private static final String[] FILE_ENDING_MSWORD = new String[]{".doc", ".dot"};
//    // application/vnd.ms-excel
//    private static final String[] FILE_ENDING_MSEXCEL = new String[]{".xls", ".xla"};
//    // application/vnd.ms-powerpoint
//    private static final String[] FILE_ENDING_MSPPT = new String[]{".ppt"};
//    // application/pdf
//    private static final String[] FILE_ENDING_PDF = new String[]{".pdf"};
//    // text/html
//    private static final String[] FILE_ENDING_HTML = new String[]{".htm", ".html"};
//    // text/plain
//    private static final String[] FILE_ENDING_TEXT = new String[]{".txt"};
//    // application/mshelp
//    private static final String[] FILE_ENDING_MSHELP = new String[]{".chm"};
//    // image/*
//    private static final String[] FILE_ENDING_IMAGE = new String[]{".png", ".gif", ".jpg", ".bmp"};
//    // video/*
//    private static final String[] FILE_ENDING_VIDEO = new String[]{".3gp", ".mp4"};
//    // audio/*
//    private static final String[] FILE_ENDING_AUDIO = new String[]{".m4a", ".mp3", ".mid", ".xmf", ".ogg", ".wav"};
//    // application/zip
//    private static final String[] FILE_ENDING_PACKAGE = new String[]{".jar", ".zip", ".rar", ".gz"};
//    // application/vnd.android.package-archive
//    private static final String[] FILE_ENDING_APK = new String[]{".apk"};
//
//    private static final String Tag = "FileUtil";
//
//    public static final String SD_PATH = Environment.getExternalStorageDirectory() + "/nursestatuin/";
//
//    /**
//     * XML解析编码方式 UTF-8
//     */
//    public static final String XML_ENCODING = "UTF-8";
//    public static final String MD5_ENCODING = "UTF-8";
//    public static final String DATA_PATTERN = "yyyy-MM-dd";
//    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
//
//    /**
//     * TXT编码方式
//     */
//    public static final String ENCODING_UTF8 = "utf-8";
//    public static final String ENCODING_UNICODE = "unicode";
//    public static final String ENCODING_UTF16BE = "utf-16be";
//    public static final String ENCODING_UTF16LE = "utf-16le";
//    public static final String ENCODING_GBK = "GBK";
//    public static final String ENCODING_GB2312 = "gb2312";
//
//    /**
//     * 回车符 /n
//     */
//    public static final String XML_ENTER_SIGN = "\n";
//
//    /**
//     * 默认构造方法
//     */
//    private FileUtil() {
//    }
//
//    public static void copyFile(File from, File to) throws Exception {
//        byte[] buff = new byte[1024];
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//            fis = new FileInputStream(from);
//            fos = new FileOutputStream(to);
//            int len = 0;
//            while ((len = fis.read(buff)) > 0) {
//                fos.write(buff, 0, len);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            fos.close();
//            fis.close();
//        }
//    }
//
//    /**
//     * 判断文件是否存在
//     *
//     * @param path 文件路径（带扩展名）
//     * @return 是否存在
//     */
//    public static boolean havaFile(final String path) {
//        if (StringUtils.isNotEmpty(path)) {
//            File file = new File(path);
//            return file.exists();
//        } else {
//            Log.e(Tag, "path: " + path + " ,Can not determine if a file exists!");
//            FileUtil.saveLog("无法判断附件是否存在:" + path);
//            return false;
//        }
//    }
//
//    /**
//     * 检测路径是否存在，不存在则创建
//     *
//     * @param path 路径
//     */
//    public static void makeDir(final String path) {
//        if (StringUtils.isNotEmpty(path)) {
//            File file = new File(path);
//            if (!file.isDirectory()) { // 判断文件夹是否存在
//                file.mkdirs(); // 创建文件夹包括创建必需但不存在的父目录
//            }
//        } else {
//            Log.e(Tag, " path:" + path + ",create path error!");
//            FileUtil.saveLog("创建文件夹失败:" + path);
//        }
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param fileName 文件名
//     * @author 李辉
//     * @date 2010-3-17 下午01:16:17
//     */
//    public static void delFile(final String fileName) {
//        if (StringUtils.isNotEmpty(fileName)) {
//            File myFile = new File(fileName);
//            if (myFile != null && myFile.exists()) {
//                FileUtil.saveLog("删除文件：" + fileName);
//                myFile.delete();
//            } else {
//                Log.e(Tag, "Delete file error! " + fileName);
//                FileUtil.saveLog("删除文件夹失败:" + fileName);
//            }
//        }
//    }
//
//    public static void delDirFile(final File resFile) {
//        try {
//            if (resFile.isDirectory()) {
//                File[] fileList = resFile.listFiles();
//                for (File file : fileList) {
//                    delDirFile(file);
//                }
//                resFile.delete();
//            } else {
//                resFile.delete();
//            }
//
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//    /**
//     * 判断文件MimeType的方法
//     *
//     * @param context       Context
//     * @param fileExtension 文件扩展名
//     * @return MimeType
//     * @author 李辉
//     * @date 2010-3-17 下午03:34:29
//     */
//    public static String getMIMEType(final Context context, final String fileExtension) {
//        StringBuffer type = new StringBuffer("");
//        if (StringUtils.isNotEmpty(fileExtension)) {
//            if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_MSWORD)) {
//                type.append("application/msword");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_MSEXCEL)) {
//                type.append("application/vnd.ms-excel");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_MSPPT)) {
//                type.append("application/vnd.ms-powerpoint");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_PDF)) {
//                type.append("application/pdf");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_TEXT)) {
//                type.append("text/plain");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_HTML)) {
//                type.append("text/html");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_MSHELP)) {
//                type.append("application/mshelp");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_IMAGE)) {
//                type.append("image/*");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_VIDEO)) {
//                type.append("video/*");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_AUDIO)) {
//                type.append("audio/*");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_PACKAGE)) {
//                type.append("application/zip");
//            } else if (checkEndsWithInStringArray(fileExtension, FILE_ENDING_APK)) {
//                type.append("application/vnd.android.package-archive");
//            } else {
//                /* 如果无法直接打开，就跳出软件列表给用户选择 */
//                type.append("*/*");
//            }
//        }
//        return type.toString();
//    }
//
//    /**
//     * 检查文件扩展名是否存在与数组中
//     *
//     * @param checkItsEnd 扩展名(或文件名)
//     * @param fileEndings 数组
//     * @return 是true，否false
//     * @author 李辉
//     * @date 2010-3-17 下午07:59:13
//     */
//    private static boolean checkEndsWithInStringArray(final String checkItsEnd, final String[] fileEndings) {
//        if (StringUtils.isNotEmpty(checkItsEnd)) {
//            for (String aEnd : fileEndings) {
//                if (checkItsEnd.endsWith(aEnd))
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 搜索手机中的文件（只查询根下的文件，未做递归操作，考虑到手机硬件是否能负荷程序的大规模搜索）
//     *
//     * @param filePath 文件路径，默认是根
//     * @param keyword  文件名包含的文字(小技巧，输入“.”可以查询目录下所有文件，应文件都是有扩展名的)
//     * @return 找到的文件
//     * @author 李辉
//     * @date 2010-2-23 下午05:55:12
//     */
//    public static List<String> searchFile(String filePath, final String keyword) {
//        long start = System.currentTimeMillis();
//        long end = 0;
//        long time = 0;
//        if (StringUtils.isEmpty(filePath)) {
//            filePath = "/";
//        }
//        List<String> result = null;
//        if (keyword != null) {
//            File[] files = new File(filePath).listFiles(); // 列出根目录下的文件
//            if (files != null && files.length > 0) {
//                result = new ArrayList<String>(files.length);
//                Log.i(Tag, "Search file from : " + filePath + "  and keyword is :" + keyword);
//                for (File f : files) {
//                    if (f.getName().indexOf(keyword) >= 0) {
//                        result.add(f.getAbsolutePath());
//                    }
//                }
//            }
//            files = null;
//        }
//        end = System.currentTimeMillis();
//        time = end - start;
//        Log.d(Tag, "Search File --  use time : " + time);
//        return result;
//    }
//
//    /**
//     * 在手机上打开文件的方法
//     *
//     * @param context Context
//     * @param file    要打开的文件
//     * @throws Exception 打开附件异常
//     * @author 李辉
//     * @date 2010-3-17 下午03:34:50
//     */
//    public static void openFile(final Context context, final File file) throws Exception {
//        long start = System.currentTimeMillis();
//        long end = 0;
//        long time = 0;
//        try {
//            if (file != null) {
//                if (file.exists()) {
//                    Intent intent = new Intent();
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setAction(Intent.ACTION_VIEW);
//
//                    // 取得MimeType
//                    String type = getMIMEType(context, getFileEnds(file.getName()));
//                    // 设置intent的file与MimeType
//                    intent.setDataAndType(Uri.fromFile(file), type);
//                    Log.i(Tag, "Open file : " + file.getAbsolutePath());
//                    context.startActivity(intent);
//                } else {
//                    String log = "Open file error, file doesn't exist !" + file.getAbsolutePath();
//                    Log.e(Tag, log);
//                    FileUtil.saveLog("打开文件失败,文件不存在:" + file.getAbsolutePath());
//                }
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        end = System.currentTimeMillis();
//        time = end - start;
//        Log.d(Tag, "Open File --  use time : " + time);
//    }
//
//    /**
//     * 通过流写文件到存储卡中
//     *
//     * @param is       文件流
//     * @param fileEnds 文件扩展名
//     * @return 文件存储的路径, 为null时表示存储失败
//     * @author 李辉
//     * @date 2010-3-17 下午02:21:51
//     */
//    public static String downloadFileFromStream(InputStream is, String fileEnds) {
//        long start = System.currentTimeMillis();
//        long end = 0;
//        long time = 0;
//        String filePath = null;
//        FileOutputStream fos = null;
//        if (is != null) {
//            try {
//                if (fileEnds == null)
//                    fileEnds = "";
//                // 创建的临时文件（createTempFile是创建临时文件，临时文件的名称有系统自动指定）
//                makeDir(PathUtil.getInstance().getSystemPath());
//                File myTempFile = File.createTempFile("temp", fileEnds,
//                        new File(PathUtil.getInstance().getSystemPath()));
//
//                filePath = myTempFile.getAbsolutePath();
//                Log.i(Tag, "Save file to : " + filePath);
//
//                fos = new FileOutputStream(myTempFile);
//                byte[] buf = new byte[512];
//                do {
//                    int numread = is.read(buf);
//                    if (numread <= 0) {
//                        break;
//                    }
//                    fos.write(buf, 0, numread);
//                } while (true);
//
//            } catch (Exception ex) {
//                Log.e(Tag, "Download file from stream error: " + ex.getMessage(), ex);
//                FileUtil.saveLog("下载附加失败:" + ex.getMessage());
//            } finally {
//                try {
//                    if (fos != null) {
//                        fos.close();
//                        fos = null;
//                    }
//                    if (is != null) {
//                        is.close();
//                        is = null;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        end = System.currentTimeMillis();
//        time = end - start;
//        Log.d(Tag, "Download File From Stream --  use time : " + time);
//        return filePath;
//    }
//
//    public static long getFileSizes(File f) throws Exception {// 取得文件大小
//        long s = 0;
//        if (f.exists()) {
//            FileInputStream fis = null;
//            fis = new FileInputStream(f);
//            s = fis.available();
//        } else {
//            f.createNewFile();
//            System.out.println("文件不存在");
//        }
//        return s;
//    }
//
//    /**
//     * 读取文件
//     *
//     * @param fileName 文件名称
//     * @return 文件内容
//     * @author 李辉
//     * @date 2010-5-24 下午03:33:50
//     */
//    public static StringBuffer readFile(final String fileName) {
//        StringBuffer sb = new StringBuffer("");
//        if (StringUtils.isNotEmpty(fileName)) {
//            // 判断手机是否有SD卡
//            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                if (FileUtil.havaFile(PathUtil.getInstance().getSystemPath() + fileName)) {
//                    FileInputStream fis = null;
//                    InputStreamReader re = null;
//                    try {
//                        fis = new FileInputStream(PathUtil.getInstance().getSystemPath() + fileName);
//                        re = new InputStreamReader(fis, XML_ENCODING);
//                        char[] buf = new char[512];
//                        int len;
//                        while ((len = re.read(buf)) != -1) {
//                            sb.append(buf, 0, len);
//                        }
//                    } catch (IOException e) {
//                        Log.e(Tag, "Read xml file error !" + e.getMessage());
//                    } finally {
//                        try {
//                            if (re != null) {
//                                re.close();
//                                re = null;
//                            }
//                            if (fis != null) {
//                                fis.close();
//                                fis = null;
//                            }
//                        } catch (final IOException e) {
//                            Log.e(Tag, e.getMessage());
//                        }
//                    }
//                }
//            }
//        }
//        return sb;
//    }
//
//    public static StringBuffer readFile(final String filePath, final String fileName) {
//        StringBuffer sb = new StringBuffer("");
//        if (StringUtils.isNotEmpty(fileName)) {
//            if (FileUtil.havaFile(filePath + fileName)) {
//                FileInputStream fis = null;
//                InputStreamReader re = null;
//                try {
//                    fis = new FileInputStream(filePath + fileName);
//                    re = new InputStreamReader(fis, ENCODING_UTF8);
//                    char[] buf = new char[512];
//                    int len;
//                    while ((len = re.read(buf)) != -1) {
//                        sb.append(buf, 0, len);
//                    }
//                } catch (IOException e) {
//                    Log.e(Tag, "Read txt file error !" + e.getMessage());
//                } finally {
//                    try {
//                        if (re != null) {
//                            re.close();
//                            re = null;
//                        }
//                        if (fis != null) {
//                            fis.close();
//                            fis = null;
//                        }
//                    } catch (final IOException e) {
//                        Log.e(Tag, e.getMessage());
//                    }
//                }
//            }
//        }
//        return sb;
//    }
//
//    /**
//     * 获取文件扩展名
//     *
//     * @param fileName 文件名
//     * @return 文件扩展名, 以.开头
//     * @author 李辉
//     * @date 2010-3-17 下午01:15:19
//     */
//    public static String getFileEnds(final String fileName) {
//        String result = "";
//        if (StringUtils.isNotEmpty(fileName)) {
//            String fileExtension = fileName;
//            fileExtension = (fileExtension.substring(fileExtension.lastIndexOf(".") + 1)).toLowerCase();
//            if (StringUtils.isNotEmpty(fileExtension) && fileExtension.length() <= 4) {
//                result = fileExtension;
//            }
//        }
//        if (StringUtils.isNotEmpty(result)) {
//            return "." + result;
//        } else {
//            return "";
//        }
//    }
//
//    /**
//     * 追加方式保存文件
//     *
//     * @param log 日志
//     */
//    public static void saveLog(String log) {
//        if (StringUtils.isNotEmpty(log)) {
//            // 判断手机是否有SD卡
//            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                final Date date = new Date();
//                SimpleDateFormat df = null;
//                String returnValue = "";
//                df = new SimpleDateFormat(DATA_PATTERN);
//                final String name = "log" + df.format(date) + ".txt";
//                df = new SimpleDateFormat(TIME_PATTERN);
//                log = XML_ENTER_SIGN + df.format(date) + ":  " + log;
//                saveFile(PathUtil.getInstance().getLogPath(), name, log, true);
//            }
//        }
//    }
//
//    /**
//     * 保存文件
//     * <p>
//     * 编码方式：UTF-8
//     *
//     * @param filePath 文件路径（“/”结尾）
//     * @param fileName 文件名
//     * @param str      保存的数据
//     * @param append   是否最佳记录
//     * @author 李辉
//     * @date 2010-5-24 下午01:31:15
//     */
//    public static synchronized void saveFile(final String filePath, final String fileName, final String str,
//                                             final boolean append) {
//        if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(str)) {
//            // 判断手机是否有SD卡
//            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//                FileOutputStream fos = null;
//                OutputStreamWriter out = null;
//                try {
//                    makeDir(filePath);
//                    final File file = new File(filePath, fileName);
//                    fos = new FileOutputStream(file, append);
//                    out = new OutputStreamWriter(fos, XML_ENCODING);
//                    out.write(str);
//                    out.close();
//                } catch (final Exception e) {
//                    Log.e(Tag, "Save log to file error !" + e.getMessage());
//                } finally {
//                    try {
//                        if (out != null) {
//                            out.close();
//                            out = null;
//                        }
//                        if (fos != null) {
//                            fos.close();
//                            fos = null;
//                        }
//                    } catch (IOException e) {
//                        Log.e(Tag, e.getMessage());
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 保存文件
//     *
//     * @param is
//     * @param filePath
//     * @param fileName
//     */
//    public static void saveFile(InputStream is, String filePath, String fileName) {
//        try {
//            File file = new File(filePath, fileName);
//            File path = new File(filePath);
//            if (file.exists()) {
//                file.delete();
//            } else if (!path.isDirectory()) {
//                path.mkdirs();
//            }
//            FileOutputStream fos = new FileOutputStream(file);
//            BufferedInputStream bis = new BufferedInputStream(is);
//            byte[] buffer = new byte[1024];
//            int len;
//            while ((len = bis.read(buffer)) != -1) {
//                fos.write(buffer, 0, len);
//                fos.flush();
//            }
//            fos.close();
//            bis.close();
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void transferFile(String srcFileName, String destFileName) throws IOException {
//        String line_separator = System.getProperty("line.separator");
//        FileInputStream fis = new FileInputStream(srcFileName);
//        StringBuffer content = new StringBuffer();
//        DataInputStream in = new DataInputStream(fis);
//        BufferedReader d = new BufferedReader(new InputStreamReader(in, "GBK"));// ,
//        // "UTF-8"
//        String line = null;
//        while ((line = d.readLine()) != null)
//            content.append(line + line_separator);
//        d.close();
//        in.close();
//        fis.close();
//        Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName), "utf-8");
//        ow.write(content.toString());
//        ow.close();
//    }
//
//    /**
//     * @param filePath 文件路径
//     * @return txt文件编码类型
//     */
//    public static String getFileEncoding(String filePath) {
//        String encoding = "";
//        FileInputStream fis = null;
//        BufferedInputStream in = null;
//        try {
//            fis = new FileInputStream(new File(filePath));
//            // 找到文档的前三个字节并自动判断文档类型。
//            in = new BufferedInputStream(fis);
//            in.mark(4);
//            byte[] first3bytes = new byte[3];
//            in.read(first3bytes);
//            in.reset();
//            if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB && first3bytes[2] == (byte) 0xBF) {// utf-8
//                encoding = ENCODING_UTF8;
//            } else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFE) {
//                encoding = ENCODING_UNICODE;
//            } else if (first3bytes[0] == (byte) 0xFE && first3bytes[1] == (byte) 0xFF) {
//                encoding = ENCODING_UTF16BE;
//            } else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFF) {
//                encoding = ENCODING_UTF16LE;
//            } else {
//                encoding = ENCODING_GBK;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                    fis = null;
//                }
//                if (in != null) {
//                    in.close();
//                    in = null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return encoding;
//    }
//
//    public static byte[] file2Bytes(File file) {
//        int byte_size = 1024;
//        byte[] b = new byte[byte_size];
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(byte_size);
//            for (int length; (length = fileInputStream.read(b)) != -1; ) {
//                outputStream.write(b, 0, length);
//            }
//            fileInputStream.close();
//            outputStream.close();
//            return outputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static File bytes2File(byte[] b, String outputFile) {
//        BufferedOutputStream stream = null;
//        File file = null;
//        try {
//            file = new File(outputFile);
//            FileOutputStream fstream = new FileOutputStream(file);
//            stream = new BufferedOutputStream(fstream);
//            stream.write(b);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//        return file;
//    }
//}