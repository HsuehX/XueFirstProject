package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by xue on 2018/4/19.
 */

public class NetControlPictureSizeActivity extends Activity {
    private Button photograph;// 拍照
    private Button upload;// 上传
    private String PHOTO_FOLDER = new File(
            Environment.getExternalStorageDirectory(), "").getPath()
            + "/myAndroid/";
    private String PHOTO_NAME = "";
    private String actionUrl = "http://11.206.128.150:8080/TestFileUploadService/upload.jsp";//上传路径
    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netcontrol_picture_size);
        init();
        setonlitener();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.arg1) {
                    case 1:
                        upload.setText("上传成功");
                        break;
                    case 2:
                        upload.setText("上传失败");
                    default:
                        break;
                }
            }
        };
    }

    public void init() {
        File file = new File(PHOTO_FOLDER);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        photograph = (Button) findViewById(R.id.photograph);
        upload = (Button) findViewById(R.id.upload);
    }


    public void setonlitener() {
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                PHOTO_NAME = new DateFormat().format("yyyyMMdd_hhmmss",
                        Calendar.getInstance(Locale.CHINA)) + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(PHOTO_FOLDER + PHOTO_NAME)));
                startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override
                    public void run() {
// TODO Auto-generated method stub
                        uploadFile(new File(PHOTO_FOLDER + PHOTO_NAME));
                    }
                }).start();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
            try {
                Bitmap take = resizeBitmap(getBitmapForFile(PHOTO_FOLDER + PHOTO_NAME), 640);
                File file = new File(PHOTO_FOLDER + PHOTO_NAME);

                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(file));
//                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);// 将图片压缩到流中
                take.compress(Bitmap.CompressFormat.JPEG, 100, bos);// 将图片压缩到流中
                bos.flush();// 输出
                bos.close();// 关闭

                ((ImageView) findViewById(R.id.imageView)).setImageBitmap(take);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float temp = ((float) height) / ((float) width);
        int newHeight = (int) ((newWidth) * temp);
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
// resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
// matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }


    public static Bitmap getBitmapForFile(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        return bitmap;
    }


    public void uploadFile(File file) {
        Message msgMessage = new Message();
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try {
            URL url = new URL(actionUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(TIME_OUT);
            con.setConnectTimeout(TIME_OUT);
            con.setDoInput(true);// 允许输入流
            con.setDoOutput(true);// 允许输出流
            con.setUseCaches(false);// 不允许使用缓存
            con.setRequestMethod("POST");// 请求方式
            con.setRequestProperty("Charset", "UTF-8");// 设置编码
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            if (file != null) {
/* 设置DataOutputStream */
                DataOutputStream ds = new DataOutputStream(con.getOutputStream());
                ds.writeBytes(twoHyphens + boundary + end);
                ds.writeBytes("Content-Disposition: form-data; "
                        + "name=\"file1\";filename=\"" + PHOTO_NAME + "\""
                        + end);
                ds.writeBytes(end);
/* 取得文件的FileInputStream */
                FileInputStream fStream = new FileInputStream(file);
/* 设置每次写入1024bytes */
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
/* 从文件读取数据至缓冲区 */
                while ((length = fStream.read(buffer)) != -1) {
/* 将资料写入DataOutputStream中 */
                    ds.write(buffer, 0, length);
                }
                ds.writeBytes(end);
                ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
/* close streams */
                fStream.close();
                ds.flush();
/* 取得Response内容 */
                InputStream is = con.getInputStream();
                int ch;
                StringBuffer b = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    b.append((char) ch);
                }
                msgMessage.arg1 = 1;
/* 关闭DataOutputStream */
                ds.close();
            }
        } catch (Exception e) {
            msgMessage.arg1 = 2;
            e.printStackTrace();
        } finally {
            handler.sendMessage(msgMessage);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file = new File(PHOTO_FOLDER + PHOTO_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
