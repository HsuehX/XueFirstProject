package com.example.lenovo.textviewspannerdalogexercise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;


/**
 * Created by xue on 2018/1/31.
 */

public class MultipleRadioView extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    private TextView mName;
    private RadioGroup mRadioGroup;
    private RadioButton mRunningRtn;
    private RadioButton mFaultRbtn;
    private RadioButton mSpareRbtn;
    private RadioButton rbtn;
    private String selectText;

    public MultipleRadioView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.view_multiple_radio, this);
        mName = (TextView) findViewById(R.id.cabinet_state_name_tv);
        mRadioGroup = (RadioGroup) findViewById(R.id.state_rp);
        mRunningRtn = (RadioButton) findViewById(R.id.cabinet_running_rb);
        mFaultRbtn = (RadioButton) findViewById(R.id.cabinet_fault_rb);
        mSpareRbtn = (RadioButton) findViewById(R.id.cabinet_spare_rb);
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    public void setLabelText(String label, String runningText, String faultText, String spareText, String kpiValue) {
        mName.setText(label);
        mRunningRtn.setText(runningText);
        mFaultRbtn.setText(faultText);
        mSpareRbtn.setText(spareText);
        mRunningRtn.setChecked(true);
        if (runningText != null && (runningText.contains("no") || runningText.contains("NO"))) {
            mRunningRtn.setChecked(true);
            mFaultRbtn.setChecked(false);
            mSpareRbtn.setChecked(false);

        } else if (faultText != null && (faultText.contains("no") || faultText.contains("NO"))) {
            mFaultRbtn.setChecked(true);
            mRunningRtn.setChecked(false);
            mSpareRbtn.setChecked(false);
        } else if (spareText != null && (spareText.contains("no") || spareText.contains("NO"))) {
            mSpareRbtn.setChecked(true);
            mFaultRbtn.setChecked(false);
            mRunningRtn.setChecked(false);
        }
//        selectText = mRunningRtn.getText().toString();
        selectText = kpiValue;
    }


    public String getSelectText() {
        return selectText;
    }

    public String getLabelName() {
        return mName.getText().toString();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        rbtn = (RadioButton) findViewById(i);
        selectText = "" + rbtn.getText();


        if (mChangeRbtnListener != null) {
            mChangeRbtnListener.changeRbtn(radioGroup, i);
        }
    }

    /**
     * 1、线建立一个接口
     */
    public interface ChangeRbtnListener {
        void changeRbtn(RadioGroup var1, int var2);
    }

    /**
     * 2、用已经建立的接口声明一个变量
     */
    ChangeRbtnListener mChangeRbtnListener;

    /**
     * 3、getter/setter方法中的set方法
     */
    public void setChangeRbtnListener(ChangeRbtnListener listener) {
        this.mChangeRbtnListener = listener;
    }
}
