package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.App;
import com.example.lenovo.textviewspannerdalogexercise.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xue on 2018/4/23.
 */

public class PhotoCutting7Activity extends Activity {
    private static final String TAG = App.class.getName();
    String[] PERMISSION_STORAGES = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写内存卡的权限
            Manifest.permission.READ_EXTERNAL_STORAGE,//读内存卡的权限
    };
    private static final int REQUEST_CODE_STORAGE = 1;
    public static final int PHOTO_REQUEST_TAKEPHOTO = 2;// 拍照
    public static final int PHOTO_REQUEST_CUT = 3;// 结果


    ImageView iv_show;
    public File tempFile;
    private Uri cropImageUri;

    private final String IMAGE_DIR = Environment.getExternalStorageDirectory() + "/azzzzz/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_cutting);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        requestStoragePermission(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_takephoto:
//                startCamera();
                camera();
                break;

        }
    }

    /**
     * 使用data中的路径，在sd卡中不可见
     */
    //开启手机摄像头
    private void startCamera() {
//        String status = Environment.getExternalStorageState();
//        if (status.equals(Environment.MEDIA_MOUNTED)) {
//            tempFile = new File(getExternalCacheDir(), getPhotoFileName());//SD卡的应用关联缓存目录
//            Log.i(TAG, "tempFile：" + tempFile + "");
//            try {
//                if (tempFile.exists()) {
//                    tempFile.delete();
//                }
//                tempFile.createNewFile();
//            } catch (Exception e) {
//                Toast.makeText(PhotoCutting7Activity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            Toast.makeText(PhotoCutting7Activity.this, "没有储存卡", Toast.LENGTH_LONG).show();
//        }
//        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // 指定调用相机拍照后照片的储存路径
//        Uri uri = null;
//        if (Build.VERSION.SDK_INT >= 24) {
//            uri = FileProvider.getUriForFile(PhotoCutting7Activity.this, "com.example.lenovo.textviewspannerdalogexercise", tempFile);
//            Log.i(TAG, "uri：" + uri + "");
//        } else {
//            uri = Uri.fromFile(tempFile);
//        }
//        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
    }

    /**
     * 将图片存储到SD卡中的课件路径下，在相册和手机中的内部存储是可以找到的
     */
    public void camera() {
        if (hasSdcard()) { // 判断存储卡是否可以用，可用进行存储
            //判断是否有读写sd卡权限如果没有则给出提示并且返回
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                return;
            }
            //创建相片存储的文件夹和路径
            File dir = new File(IMAGE_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }
            tempFile = new File(dir, "/temp_photo" + System.currentTimeMillis() + "_" + "temp_photo.jpg");
            //创建打开相机的intent  将路径作为参数传给intent
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (android.os.Build.VERSION.SDK_INT < 24) {
                // 7.0以下系统走此分支
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues));
            }
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);  //打开相机
        } else {
            Toast.makeText(this, "未找到存储卡，无法拍照！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断sdcard是否被挂载
     */
    public boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 申请权限 SD卡的读写权限
     *
     * @param activity
     */
    private void requestStoragePermission(Activity activity) {
        //检测权限
        int permission = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //没有权限 则申请权限  弹出对话框
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGES, REQUEST_CODE_STORAGE);
        }
    }

    /**
     * 申请权限结果回调
     *
     * @param requestCode  请求码
     * @param permissions  申请权限的数组
     * @param grantResults 申请权限成功与否的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //申请成功
                    Toast.makeText(PhotoCutting7Activity.this, "授权SD卡权限成功", Toast.LENGTH_SHORT).show();
                } else {
                    //申请失败
                    Toast.makeText(PhotoCutting7Activity.this, "授权SD卡权限失败 可能会影响应用的使用", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:// 当选择拍照时调用
                Uri uri = null;
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(PhotoCutting7Activity.this, "com.example.lenovo.textviewspannerdalogexercise", tempFile);
                } else {
                    uri = Uri.fromFile(tempFile);
                }
                startPhotoZoom(uri);

                break;
            case PHOTO_REQUEST_CUT:// 返回的结果
                if (resultCode == RESULT_OK) {
                    if (data != null)
                        try {
                            Bitmap bp = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
                            iv_show.setImageBitmap(bp);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                }
                break;
        }
    }

    public void startPhotoZoom(Uri uri) {
        File CropPhoto = new File(getExternalCacheDir(), "crop.jpg");
        try {
            if (CropPhoto.exists()) {
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        cropImageUri = Uri.fromFile(CropPhoto);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");//设置了参数，就会调用裁剪，如果不设置，就会跳过裁剪的过程。
        intent.putExtra("scale", true);

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1.1);//这里的比例只要不是1:1，裁剪的框就不是圆形

        //输出的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection 如果你需要特定的比例去裁剪图片，那么这个一定要去掉，因为它会破坏掉特定的比例。
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

