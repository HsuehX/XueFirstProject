package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by xue on 2018/10/31.
 */

public class WriteDetailedActivity extends Activity implements View.OnClickListener {
    private TextView mWriteTv;
    private TextView mReadTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_detailed);
        init();
    }

    private void init() {
        mWriteTv = (TextView) findViewById(R.id.tv_write);
        mReadTv = (TextView) findViewById(R.id.tv_read);
        mWriteTv.setOnClickListener(this);
        mReadTv.setOnClickListener(this);
    }

    private void writeData() {
        String filePath = "/sdcard/aa/";
        String fileName = "data.txt";
        writeTxtToFile("Wx:lcti1314", filePath, fileName);
    }

    private int frequency = 100;

    // 将字符串写入到文本文件中
    private void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
//        final String[] strContent = {strcontent + "\r\n"};
        final String[] strContent = {""};
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            final RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent[0].getBytes());

            final Handler handler = new Handler();
            handler.post(new Runnable() {
                private int k = 0;

                public void run() {
                    k++;
                    if (k <= frequency) {
                        strContent[0] = k + "hello";
                        mReadTv.setText(strContent[0]);
                        handler.postDelayed(this, 10);
                    }
                }
            });

            for (int i = 0; i < frequency; i++) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                strContent[0] = i + "hello" + "\r\n";
                raf.write(strContent[0].getBytes());
            }
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

//生成文件

    private File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

//生成文件夹

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_write:
                writeData();
                break;
            case R.id.tv_read:
                File file = new File("/sdcard/aa/data.txt");
                String fileString = getFileContent(file);
                mReadTv.setText(fileString);
                break;
            default:
                break;
        }
    }


    //读取指定目录下的所有TXT文件的文件内容
    private String getFileContent(File file) {
        String content = "";
        if (!file.isDirectory()) {  //检查此路径名的文件是否是一个目录(文件夹)
            if (file.getName().endsWith("txt")) {//文件格式为""文件
                try {
                    InputStream instream = new FileInputStream(file);
                    if (instream != null) {
                        InputStreamReader inputreader
                                = new InputStreamReader(instream, "UTF-8");
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = "";
                        //分行读取
                        while ((line = buffreader.readLine()) != null) {
                            content += line + "\n";
                        }
                        instream.close();//关闭输入流
                    }
                } catch (java.io.FileNotFoundException e) {
                    Log.d("TestFile", "The File doesn't not exist.");
                } catch (IOException e) {
                    Log.d("TestFile", e.getMessage());
                }
            }
        }
        return content;
    }
}
