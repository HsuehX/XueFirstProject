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

public class YesOrNoRadioView extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    private TextView mName;
    private RadioGroup mRadioGroup;
    private RadioButton mYesRbtn;
    private RadioButton mNoRbtn;
    private RadioButton rbtn;
    private String selectText;

    public YesOrNoRadioView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.view_yn_radio, this);
        mName = (TextView) findViewById(R.id.cabinet_isrunning_normal_tv);
        mRadioGroup = (RadioGroup) findViewById(R.id.isture_rg);
        mYesRbtn = (RadioButton) findViewById(R.id.isnormal_ture_rb);
        mNoRbtn = (RadioButton) findViewById(R.id.isnormal_false_rb);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    public void setLabelText(String label, String yesText, String noText) {
        mName.setText(label);
        mYesRbtn.setText(yesText);
        mNoRbtn.setText(noText);
        mYesRbtn.setChecked(true);
        selectText = mYesRbtn.getText().toString();
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
    }
}