//package com.example.lenovo.textviewspannerdalogexercise.activity.photo;
//
//import android.app.Application;
//import android.content.Context;
//
///**
// * Created by xue on 2018/4/19.
// * 拍照的application 我觉得应该是用不上的
// */
//
//public class PhotoApplication extends Application {
//
//    protected static PhotoApplication kevinApplication = null;
//    /** 上下文 */
//    protected Context mContext          = null;
//    /** Activity 栈 */
//    public ActivityStack mActivityStack = null;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // 由于Application类本身已经单例，所以直接按以下处理即可
//        kevinApplication = this;
//        mContext = getApplicationContext();     // 获取上下文
//        mActivityStack = new ActivityStack();   // 初始化Activity 栈
//
//        initConfiguration();
//    }
//
//    /**
//     * 获取当前类实例对象
//     * @return
//     */
//    public static PhotoApplication getInstance(){
//        return kevinApplication;
//    }
//
//    /**
//     * @description 初始化配置文件
//     *
//     * @return void
//     * @date 2015-5-22 10:44:48
//     */
//    private void initConfiguration() {
//
//    }
//}
