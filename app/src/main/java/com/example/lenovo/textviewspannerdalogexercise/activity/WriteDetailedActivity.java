package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import java.io.File;
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

    // 将字符串写入到文本文件中
    private void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());

            for (int i = 0; i < 10; i++) {
                strContent = strContent + i + "";
                raf.write(strContent.getBytes());
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
                break;
            default:
                break;
        }
    }
}
