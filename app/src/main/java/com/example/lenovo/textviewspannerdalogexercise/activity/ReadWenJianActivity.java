package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.log.FileUtilBh;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xue on 2018/3/29.
 */

public class ReadWenJianActivity extends Activity {
    @Bind(R.id.read_tv)
    TextView read_tv;
    @Bind(R.id.show_tv)
    TextView show_tv;
    @Bind(R.id.text_tv)
    TextView text_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_wenjian);
        ButterKnife.bind(this);
        init();
        initType();
        initLines();
    }

    private void initLines() {
        File sdCardDir = Environment.getExternalStorageDirectory();
        String filename = sdCardDir + "/" + "Self-taught.txt";

        //方式一：通过构造方法
        String str2 = new String(readFile(sdCardDir + "/", "Self-taught.txt"));
        System.out.println(str2);
        Log.e("文本", str2);
        text_tv.setText(str2);

        //方式二：通过toString方法
        String str3 = readFile(filename, "Self-taught.txt").toString();
        System.out.println(str3);
    }


    public static StringBuffer readFile(final String filePath, final String fileName) {
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotEmpty(fileName)) {
            if (FileUtilBh.havaFile(filePath + fileName)) {
                FileInputStream fis = null;
                InputStreamReader re = null;
                try {
                    fis = new FileInputStream(filePath + fileName);
                    re = new InputStreamReader(fis, "GB2312");
                    char[] buf = new char[512];
                    int len;
                    while ((len = re.read(buf)) != -1) {
                        sb.append(buf, 0, len);
                    }
                } catch (IOException e) {
                    Log.e("", "Read txt file error !" + e.getMessage());
                } finally {
                    try {
                        if (re != null) {
                            re.close();
                            re = null;
                        }
                        if (fis != null) {
                            fis.close();
                            fis = null;
                        }
                    } catch (final IOException e) {
                        Log.e("", e.getMessage());
                    }
                }
            }
        }
        return sb;
    }

    private void initType() {
        File sdCardDir = Environment.getExternalStorageDirectory();
        String filename = sdCardDir + "/" + "Self-taught.txt";
//        String filename = sdCardDir + "/" + "app.ini";
        BufferedReader reader = null;
        String strLine = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "GB2312"));
            strLine = reader.readLine();

//            reader.lines().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String temp1 = EncodingUtils.getString(strLine.getBytes(), "GB2312");
//        String temp2 = EncodingUtils.getString(strLine.getBytes("utf-8"), "utf-8");
        String temp3 = EncodingUtils.getString(strLine.getBytes(), "utf-8");
        Log.e("文本", temp3);

        read_tv.setText(temp3);
    }

    private void init() {
        File sdCardDir = Environment.getExternalStorageDirectory();
//        String filename = sdCardDir + "/" + "Self-taught.txt";
        String filename = sdCardDir + "/" + "app.ini";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            if ((line = bufferedReader.readLine()) != null) {
                Toast.makeText(this, line, Toast.LENGTH_SHORT).show();
                show_tv.setText(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
