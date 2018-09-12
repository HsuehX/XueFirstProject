package com.example.lenovo.textviewspannerdalogexercise.view.msi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;


public class RecordDialog {

    public final String TAG = "MSI";
    private Context context;
    private Dialog alertDialog;
    private AudioRecordMicView record_surfaceView;
    private Button bt_record;
    private Button btn_close;
    private Handler handler;
    private SpeechRecognizer mIat;
    private String iat_msg;
    private int ret = 0; // 函数调用返回值
    private OnGetRecordResultListener mImpl;

    public RecordDialog(Context context, OnGetRecordResultListener mImpl) {
        this.context = context;
        this.mImpl = mImpl;
        initListener();
    }

    private void initListener() {
        // 初始化语音引擎
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=5acdbef6");
        mIat = SpeechRecognizer.createRecognizer(context, mInitListener);
        handler = new Handler();
        setParam();
    }

    public Dialog createDialog(Display display) {
        if (alertDialog != null) {
            alertDialog = null;
        }
        alertDialog = new Dialog(context, R.style.outside_Dialog);
        RelativeLayout layoutView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.outside_dialog_record, null);
        findViews(layoutView);
        alertDialog.getWindow().setContentView(layoutView);
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int)(display.getHeight()*0.25);
        dialogWindow.setAttributes(lp);
        alertDialog.show();
        return alertDialog;
    }

    private void setParam() {
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        // 设置语言
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "20000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "20000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH,
                Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    private void findViews(View layoutView) {
        bt_record = (Button) layoutView.findViewById(R.id.bt_record);
        record_surfaceView = (AudioRecordMicView) layoutView.findViewById(R.id.record_surfaceView);
        btn_close = (Button) layoutView.findViewById(R.id.btn_close);
        bt_record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    iat_msg = "";
                    // 不显示听写对话框
                    ret = mIat.startListening(mRecognizerListener);
                    if (ret != ErrorCode.SUCCESS) {
                        Toast.makeText(context, "语音启动失败,错误码：" + ret, Toast.LENGTH_SHORT).show();
                    } else {

                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mIat.stopListening();
                }
                return false;
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog != null) {
                    mIat.stopListening();
                    alertDialog.dismiss();
                }
            }
        });
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(context, "初始化失败" + code, Toast.LENGTH_SHORT);
            }
        }
    };
    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            Log.i(TAG, "此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            Log.i(TAG, error.toString());
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
//            showTip("结束说话");
            Log.i(TAG, "此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = JsonParser.parseIatResult(results.getResultString());
            iat_msg += result;
            if (isLast) {
                record_surfaceView.setLevel(0);
                mImpl.onGetResult(iat_msg);
            }
        }

        // 音量值0~30
        @Override
        public void onVolumeChanged(int volume, byte[] data) {
//            showTip("当前正在说话，音量大小：" + volume);
            record_surfaceView.setMaxLevel(30);
            record_surfaceView.setLevel(volume);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };

    public void destroy( ) {
        mIat.destroy();
    }
    public void dismissDialog() {
        alertDialog.dismiss();
    }
}
