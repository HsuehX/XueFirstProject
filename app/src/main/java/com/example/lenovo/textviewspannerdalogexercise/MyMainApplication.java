////package com.example.lenovo.textviewspannerdalogexercise;
////
////import android.app.Application;
////import android.os.Build;
////import android.os.StrictMode;
////
////import com.nostra13.universalimageloader.core.ImageLoader;
////import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
////
/////**
//// * Created by xueww on 2017/12/10.
//// */
////
////public class MyMainApplication extends Application {
////    @Override
////    public void onCreate() {
////        super.onCreate();
////
////        //创建默认的ImageLoader配置参数
////        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
////
////        //初始化ImageLoader与配置
////        ImageLoader.getInstance().init(configuration);
////
////        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
////        StrictMode.setVmPolicy(builder.build());
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
////            builder.detectFileUriExposure();
////        }
////    }
////}
//
//
//
//
//
//
//package com.xue.permissiontest;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.yanzhenjie.permission.AndPermission;
//import com.yanzhenjie.permission.PermissionNo;
//import com.yanzhenjie.permission.PermissionYes;
//
//import java.util.List;
//
//public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
//    boolean isShow = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        requestPermissions();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (!isShow){
//
//            requestPermissions();
//        }
//    }
//
//    private void requestPermissions() {
//        if(AndPermission.hasPermission(this, Manifest.permission.READ_SMS,Manifest.permission.READ_CALENDAR,
//                Manifest.permission.CAMERA,Manifest.permission.GET_ACCOUNTS,
//                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.CALL_PHONE,Manifest.permission.BODY_SENSORS,
//                Manifest.permission.READ_EXTERNAL_STORAGE)){
//            //执行业务
//
//        }else {
//            //申请权限
//            AndPermission.with(this)
//                    .requestCode(100)
//                    .permission(Manifest.permission.READ_SMS,Manifest.permission.READ_CALENDAR,
//                            Manifest.permission.CAMERA,Manifest.permission.GET_ACCOUNTS,
//                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECORD_AUDIO,
//                            Manifest.permission.CALL_PHONE,Manifest.permission.BODY_SENSORS,
//                            Manifest.permission.READ_EXTERNAL_STORAGE)
//                    .send();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        //只需要调用这一句，第一个参数是当前Activity/Fragment，回调方法写在当前Activity/Fragment
//        AndPermission.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
//    }
//    // 成功回调的方法，用注解即可，里面的数字是请求时的requestCode。
//    @PermissionYes(100)
//    private void getSucceed(List<String> grantedPermissions) {
//        // TODO 申请权限成功。
//
//    }
//    // 失败回调的方法，用注解即可，里面的数字是请求时的requestCode。
//    @PermissionNo(100)
//    private void getDefeated(List<String> deniedPermissions) {
//        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
//        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
//            //用默认的提示语。
////            AndPermission.defaultSettingDialog(this, 1).show();
//            new AlertDialog.Builder(this)
//                    .setMessage("【用户选择了不在提示按钮，或者系统默认不在提示（如MIUI）。" +
//                            "引导用户到应用设置页去手动授权,注意提示用户具体需要哪些权限】\r\n" +
//                            "获取相关权限失败:xxxxxx,将导致部分功能无法正常使用，需要到设置页面手动授权")
//                    .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //引导用户至设置页手动授权
//                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
//                            intent.setData(uri);
//                            startActivity(intent);
//                            isShow = false;
//                        }
//                    })
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //引导用户手动授权，权限请求失败
//                            isShow = false;
//                        }
//                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    //引导用户手动授权，权限请求失败
//                    isShow = false;
//                }
//            }).show();
//            isShow = true;
//        }
//    }
//}
