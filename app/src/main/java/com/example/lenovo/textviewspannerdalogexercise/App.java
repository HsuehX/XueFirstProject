package com.example.lenovo.textviewspannerdalogexercise;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.lenovo.textviewspannerdalogexercise.db.DatabaseHelper;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.DemoDto;
import com.example.lenovo.textviewspannerdalogexercise.log.CrashHandlerBh;
import com.example.lenovo.textviewspannerdalogexercise.log.FileUtilBh;
import com.example.lenovo.textviewspannerdalogexercise.log.PathUtilBh;
import com.j256.ormlite.dao.Dao;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 全局变量
 */
public class App extends Application {
    private static final String TAG = App.class.getName();
    private static App mApplication;


    // 数据库操作
    public static DatabaseHelper mDatabaseHelper;
    /**
     * Safetoken
     */
    private String mSafeToken = null;
    /**
     * mUserToken
     */
    private String mUserToken = null;
    /**
     * customname
     */
    private String customname = null;
    /**
     * 程序包版本信息
     */
    private Integer mPackageVersionCode = null;
    private String mPackageVersionName = null;

    public static synchronized App getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        //文件创建路径
        PathUtilBh.getInstance().init(this);
        FileUtilBh.init();
        activityList = new ArrayList<Activity>();
        mDatabaseHelper = DatabaseHelper.getInstance(mApplication);
        try {
            Dao<DemoDto, Integer> demoDao = mDatabaseHelper.getDemoDao();
            demoDao.queryForId(0); // ORM数据库是首次查询时创建
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CrashHandlerBh.getInstance().init(getApplicationContext());
//        initUM();

    }

//    private void initUM() {
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//    }

    /**
     * 存储用户名密码  没用到
     */
    private void configUserInfo() {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        setCustomname(sp.getString("username", ""));
        setUserToken(sp.getString("userToken", ""));
    }

    /**
     * 配置Database
     */
    public void configDatabase() {
        PathUtilBh.getInstance().init(this);
//        FileUtilBh.init();
        activityList = new ArrayList<Activity>();

        mDatabaseHelper = DatabaseHelper.getInstance(mApplication);
        try {
            Dao<DemoDto, Integer> demoDao = mDatabaseHelper.getDemoDao();
            demoDao.queryForId(0); // ORM数据库是首次查询时创建
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CrashHandlerBh.getInstance().init(getApplicationContext());
    }

//    /**
//     * 配置ImageLoder
//     */
//    private void configImageLoader() {
//        // 初始化ImageLoader
//        @SuppressWarnings("deprecation")
//        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_iv_empty) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.icon_iv_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.icon_iv_empty) // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
//                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
//                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
//
//                .build(); // 创建配置过得DisplayImageOption对象
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(App.getInstance()).defaultDisplayImageOptions(options)
//                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
//                .diskCache(new UnlimitedDiskCache(new File(PathUtil.getInstance().getCachePath())))
//                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
//        ImageLoader.getInstance().init(config);
//    }


    private ArrayList<Activity> activityList;

    public void finishActivityProgram() {
        int size = activityList.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityList.get(i);
            if (null != activity) {
                activity.finish();
            }
        }
        activityList = new ArrayList<Activity>();
    }

    // activity管理：从列表中移除activity
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // activity管理：添加activity到列表
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 根据Activity的类名，finish掉Activity
     */
    public void popOneCertainActivity(Class<?> cls) {
        if (activityList != null && !activityList.isEmpty()) {
            int size = activityList.size();
            Activity activity;
            for (int i = size - 1; i > 0; i--) {
                activity = activityList.get(i);
                if (cls.equals(activity.getClass())) {
                    activityList.remove(i);
                    activity.finish();
                    break;
                }
            }
        }
    }

    public int getPackageVersionCode() {
        if (mPackageVersionCode == null) {
            try {
                PackageManager manager = getPackageManager();
                PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
                mPackageVersionCode = info.versionCode;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }


        return mPackageVersionCode;
    }

    public String getPackageVersionName() {
        if (mPackageVersionName == null) {
            try {
                PackageManager manager = getPackageManager();
                PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
                mPackageVersionName = info.versionName;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return mPackageVersionName;
    }

    /**
     * 停止服务
     */
    public void stopService() {

    }

    public String getSafeToken() {
        mSafeToken = "62336C782F43416D734D65637A722B72665768386A513D3D";
        return mSafeToken;
    }

    public void setSafeToken(String safeToken) {
        this.mSafeToken = safeToken;
    }

    public String getUserToken() {
        return mUserToken;
    }

    public void setUserToken(String mUserToken) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userToken", mUserToken);
        editor.commit();
        this.mUserToken = mUserToken;
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", customname);
        editor.commit();
        this.customname = customname;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public void setatabaseHelper(DatabaseHelper mDatabaseHelper) {
        App.mDatabaseHelper = mDatabaseHelper;
    }
}
