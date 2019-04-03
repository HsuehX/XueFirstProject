package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.bean.DemoBean;
import com.example.lenovo.textviewspannerdalogexercise.utils.ExcelUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/10/31.
 */

public class WriteDetailedActivity extends Activity implements View.OnClickListener {
    private TextView mWriteTv;
    private TextView mReadTv;
    private EditText mPathEt;
    private TextView mCountTv;
    private TextView mMaxTv;
    private EditText mCabinetDescribeEt;
    private TextView mContentconfirmTv;
    private TextView mAddTv;
    private TextView mReduceTv;
    private TextView text;
    private ProgressBar mProgressBar;
    private ProgressDialog mProgressDialog;
    private Button mTestExcelBtn;
    private TextView mTestExcelTv;
    private int MaxNum = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_detailed);
        init();
    }

    private void init() {
        mWriteTv = (TextView) findViewById(R.id.tv_write);
        mReadTv = (TextView) findViewById(R.id.tv_read);
        mPathEt = (EditText) findViewById(R.id.et_path);
        mCountTv = (TextView) findViewById(R.id.count_tv);
        mMaxTv = (TextView) findViewById(R.id.tv_max);
        mCabinetDescribeEt = (EditText) findViewById(R.id.et_cabinet_describe);
        mContentconfirmTv = (TextView) findViewById(R.id.tv_content_confirm);
        mAddTv = (TextView) findViewById(R.id.tv_add);
        mReduceTv = (TextView) findViewById(R.id.tv_reduce);
        text = (TextView) findViewById(R.id.tv_test_pb);
        mProgressBar = (ProgressBar) findViewById(R.id.pv_test);
        mTestExcelBtn = (Button) findViewById(R.id.btn_test_excel);
        mTestExcelTv = (TextView) findViewById(R.id.tv_test_excel);

        mWriteTv.setOnClickListener(this);
        mReadTv.setOnClickListener(this);
        text.setText("第一进度：" + mProgressBar.getProgress() * 100.0 / mProgressBar.getMax() + "%" +
                " 第二进度：" + mProgressBar.getSecondaryProgress() * 100.0 / mProgressBar.getMax() + "%");
        mAddTv.setOnClickListener(this);
        mReduceTv.setOnClickListener(this);
        mTestExcelBtn.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        mMaxTv.setText("" + MaxNum);
        mPathEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCountTv.setText(mCabinetDescribeEt.getText().toString().length() + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    String filePath = "/sdcard/aa/";
    String fileName;

    private void writeData() {
//        String fileName = "data.txt";
        fileName = mPathEt.getText().toString().trim();
        if (!TextUtils.isEmpty(fileName)) {
            writeTxtToFile(filePath, fileName);
        }
    }

    private int frequency = 100;

    // 将字符串写入到文本文件中
    private void writeTxtToFile(String filePath, String fileName) {
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

    private boolean isZero = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_write:
                writeData();
                break;
            case R.id.tv_read:
//                File file = new File("/sdcard/aa/data.txt");
//                File file = new File("/sdcard/aa/" + fileName);
//                String fileString = getFileContent(file);
                showPB();
//                mReadTv.setText(fileString);
//                mCabinetDescribeEt.setText(fileString);
                break;
            case R.id.tv_add:
                mProgressBar.incrementProgressBy(10);
                mProgressBar.setSecondaryProgress(mProgressBar.getProgress() + 30);
                isZero = false;
                break;
            case R.id.tv_reduce:
                mProgressBar.incrementProgressBy(-10);
                if (0 == mProgressBar.getProgress() && !isZero) {
                    mProgressBar.setSecondaryProgress(mProgressBar.getProgress() + 30);
                    isZero = true;
                } else if (0 == mProgressBar.getProgress() && isZero) {
                    mProgressBar.setSecondaryProgress(mProgressBar.getProgress());
                } else {
                    mProgressBar.setSecondaryProgress(mProgressBar.getProgress() + 30);
                }

                break;
            case R.id.btn_test_excel:
                exportExcel(this);
                break;
            default:
                break;
        }
        text.setText("第一进度：" + mProgressBar.getProgress() * 100.0 / mProgressBar.getMax() + "%" +
                " 第二进度：" + mProgressBar.getSecondaryProgress() * 100.0 / mProgressBar.getMax() + "%");
    }

    private void showPB() {
        if (TextUtils.isEmpty(fileName)) {
            fileName = "data.txt";
        }
        File file = new File("/sdcard/aa/" + fileName);
        final String fileString = getFileContent(file);
        // 设置水平进度条
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setIcon(R.mipmap.ic_launcher);
        mProgressDialog.setTitle("提示");

        mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(WriteDetailedActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });

        mProgressDialog.setMessage("这是一个水平进度条");
        mProgressDialog.show();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                int i = 0;
                while (i < 100 && mProgressDialog.getProgress() != 100) {
                    try {
                        Thread.sleep(200);
                        // 更新进度条的进度,可以在子线程中更新进度条进度
                        mProgressDialog.incrementProgressBy(1);
                        i++;
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                // 在进度条走完时删除Dialog
                mProgressDialog.dismiss();
                mReadTv.setText(fileString);
                mCabinetDescribeEt.setText(fileString);
            }
        }).start();
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

    private void exportExcel(Context context) {


        String filePath = "/sdcard/aa/AndroidExcelDemo";


        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }


        String excelFileName = "/demo.xls";


        String[] title = {"姓名", "年龄", "男孩"};
        String sheetName = "demoSheetName";

        List<DemoBean> demoBeanList = new ArrayList<>();
        DemoBean demoBean1 = new DemoBean("张三", 10, true);
        DemoBean demoBean2 = new DemoBean("小红", 12, false);
        DemoBean demoBean3 = new DemoBean("李四", 18, true);
        DemoBean demoBean4 = new DemoBean("王香", 13, false);
        demoBeanList.add(demoBean1);
        demoBeanList.add(demoBean2);
        demoBeanList.add(demoBean3);
        demoBeanList.add(demoBean4);
        filePath = filePath + excelFileName;
        ExcelUtil.initExcel(filePath, sheetName, title);
        ExcelUtil.writeObjListToExcel(demoBeanList, filePath + "/demo.xls", context);

        mTestExcelTv.setText("excel已导出至：" + filePath);

    }
}
