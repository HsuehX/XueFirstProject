package com.example.lenovo.textviewspannerdalogexercise.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;


/**
 * Created by xue on 2018/1/30.
 */

public class EditView extends LinearLayout implements TextWatcher, View.OnClickListener {

    private TextView mName;
    private EditText mEditText;

    public EditView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.view_edittext, this);
        mName = (TextView) findViewById(R.id.cabinet_humidity_tv);
        mEditText = (EditText) findViewById(R.id.cabinet_humidity_et);

        mName.setOnClickListener(this);
        mEditText.addTextChangedListener(this);
    }

    public void setLabelText(String label, String hint) {
        mName.setText(label);
        mEditText.setHint(hint);
    }

    public String getEditText() {
        return mEditText.getText().toString();
    }

    public String getLabelName() {
        return mName.getText().toString();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_buy_count_tv:
                break;
            default:
                break;
        }
    }
}
