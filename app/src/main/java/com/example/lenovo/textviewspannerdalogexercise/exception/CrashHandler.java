//package com.example.lenovo.textviewspannerdalogexercise.exception;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.lenovo.textviewspannerdalogexercise.App;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.lang.Thread.UncaughtExceptionHandler;
//
///**
// * 全局异常捕获句柄类
// */
//public class CrashHandler implements UncaughtExceptionHandler {
//
//    public static final String TAG = CrashHandler.class.getName();
//    private static CrashHandler mInstance;
//    private UncaughtExceptionHandler mSystemExceptionHandler; // 系统默认的UncaughtExceptionHandler
//    private Context mContext;
//
//    /**
//     * 私有构造函数
//     */
//    private CrashHandler() {
//    }
//
//    /**
//     * @return 单例
//     */
//    public static CrashHandler getInstance() {
//        if (mInstance == null) {
//            mInstance = new CrashHandler();
//        }
//        return mInstance;
//    }
//
//    /**
//     * @param context 上下文对象
//     */
//    public void init(Context context) {
//        mContext = context;
//        mSystemExceptionHandler = Thread.getDefaultUncaughtExceptionHandler(); // 获取系统默认的UncaughtExceptionHandler
//        Thread.setDefaultUncaughtExceptionHandler(this); // 设置本类对象crashHandler为程序的默认处理器
//    }
//
//    /**
//     * 当异常发生时，捕获异常
//     *
//     * @param thread 线程
//     * @param ex     异常
//     */
//    @Override
//    public void uncaughtException(Thread thread, Throwable ex) {
//        try {
//            handleException(thread, ex);
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage(), e);
//            if (mSystemExceptionHandler != null) {
//                mSystemExceptionHandler.uncaughtException(thread, ex); // 让系统默认的异常处理器来处理
//            }
//        }
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            Log.e(TAG, e.getMessage(), e);
//        }
//        App.getInstance().stopService();
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(10);
//    }
//
//    /**
//     * 自定义异常处理
//     *
//     * @param ex 异常
//     */
//    private void handleException(Thread thread, Throwable ex) {
//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
//
//        Throwable cause = ex.getCause();
//        while (cause != null) {
//            cause.printStackTrace(printWriter);
//            cause = cause.getCause();
//        }
//        printWriter.close();
//
//        String info = writer.toString();
//        Log.e(TAG, info);
//    }
//}