package com.example.lenovo.textviewspannerdalogexercise.activity.uploadimage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.network.aboutupload.UpLoadUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xue on 2018/6/28.
 */

public class ShangChuanActivity extends AppCompatActivity implements UpLoadUtil.OnUpLoadProcessListener {

    private ImageView mImageView;
    private static final int PHOTO_REQUEST = 1;//本地图片请求
    private static final int CAMERA_REQUEST = 2;//相机请求
    private static final int PHOTO_CLIP = 3;//
    private static final int UPLOAD_INIT_PROCESS = 4;//上传初始化
    protected static final int UPLOAD_FILE_DONE = 6;//上传中  这个原来设置为2  不知道为什么设置为2   我自己改成的6 也不知道有没有影响
    private static final int UPLOAD_IN_PROCESS = 5;//上传文件响应
    private ProgressDialog pd;
    private File filepath;//返回的文件地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_chuan);
        initView();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(ShangChuanActivity.this)
                        .setTitle("选择头像")
                        .setPositiveButton("相机", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getCamera();

                            }
                        })
                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getPhoto();

                            }
                        })
                        .show();
            }
        });
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }


    private void getCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "hand.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }


    private void toUploadFile() {
        pd = ProgressDialog.show(this, "", "正在上传文件...");
        pd.show();
        String fileKey = "avatarFile";
        UpLoadUtil upLoadUtil = UpLoadUtil.getInstance();
        upLoadUtil.setOnUploadProcessListener(ShangChuanActivity.this); //设置监听器监听上传状态

        Map<String, String> params = new HashMap<String, String>();//上传map对象
        params.put("userId", "");
        upLoadUtil.upLoadFile(filepath, fileKey, "上传头像的地址", params);
        Toast.makeText(this, "上传成功", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onUpLoadDone(int responseCode, String message) {
        pd.dismiss();
        Message msg = Message.obtain();
        msg.what = UPLOAD_FILE_DONE;
        msg.arg1 = responseCode;
        msg.obj = message;
    }

    @Override
    public void onUpLoadProcess(int upLoadSize) {
        //上传中
        Message msg = Message.obtain();
        msg.what = UPLOAD_IN_PROCESS;
        msg.arg1 = upLoadSize;
    }

    @Override
    public void initUpload(int fileSize) {

        //准备上传
        Message msg = Message.obtain();
        msg.what = UPLOAD_INIT_PROCESS;
        msg.arg1 = fileSize;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/hand.jpg");//保存图片
                        if (file.exists()) {
                            //对相机拍照照片进行裁剪
                            photoClip(Uri.fromFile(file));
                        }
                }
                break;

            case PHOTO_REQUEST://从相册取
                if (data != null) {
                    Uri uri = data.getData();
                    //对相册取出照片进行裁剪
                    photoClip(uri);

                }
                break;
            case PHOTO_CLIP:
                //完成
                if (data != null) {

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        try {
                            //获得图片路径
                            filepath = UpLoadUtil.saveFile(photo, Environment.getExternalStorageDirectory().toString(), "hand.jpg");
                            //上传照片
                            toUploadFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //上传完成将照片写入imageview与用户进行交互
                        mImageView.setImageBitmap(photo);
                    }
                }
                break;
        }
    }
}