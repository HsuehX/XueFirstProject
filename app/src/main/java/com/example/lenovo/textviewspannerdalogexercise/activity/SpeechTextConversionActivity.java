package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by xue on 2018/4/11.
 */

public class SpeechTextConversionActivity extends Activity implements View.OnClickListener {
    private static final String TAG = SpeechTextConversionActivity.class.getSimpleName();

    private EditText et_input;
    private Button btn_startspeech, btn_startspeektext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_text_conversion);
//        init();
    }

    private void init() {
        initView();
        initSpeech();
    }

    private void initView() {
        et_input = (EditText) findViewById(R.id.et_input);
        btn_startspeech = (Button) findViewById(R.id.btn_startspeech);
        btn_startspeektext = (Button) findViewById(R.id.btn_startspeektext);
        btn_startspeech.setOnClickListener(this);
        btn_startspeektext.setOnClickListener(this);
    }

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility.createUtility(SpeechTextConversionActivity.this, SpeechConstant.APPID + "=5acdbef6");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startspeech: //语音识别（把声音转文字）
                startSpeechDialog();
                break;
            case R.id.btn_startspeektext:// 语音合成（把文字转声音）
                speekText();
                break;
        }

    }

    private void startSpeechDialog() {
    }

    private void speekText() {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer sY = SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录 13.2
        sY.setParameter(SpeechConstant.VOICE_NAME, "");
        sY.setParameter(SpeechConstant.SPEED, "50");// 设置语速
        sY.setParameter(SpeechConstant.VOLUME, "60");// 设置音量，范围 0~100
        sY.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
    }


}
