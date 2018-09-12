package com.example.lenovo.textviewspannerdalogexercise.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by xue on 2018/4/8.
 */

public class LimitEditText extends LinearLayout {
    //    /**
//     * 描述
//     */
//    @Bind(R.id.cabinet_describe_et)
//    EditText mCabinetDescribeEt;
    private EditText mCabinetDescribeEt;
    private TextView mCountTv;
//    /**
//     * 描述输入字符数
//     */
//    @Bind(R.id.count_tv)
//    TextView mCountTv;

    Context mContext;

    public LimitEditText(Context context) {
        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.limit_edittext_view, this);
        ButterKnife.bind((Activity) mContext);
        mCabinetDescribeEt = (EditText) findViewById(R.id.cabinet_describe_et);
        mCountTv = (TextView) findViewById(R.id.count_tv);
        changeText();
    }

//    /**
//     * 描述输入框字符改变
//     *
//     * @param s
//     */
//    @OnTextChanged(value = R.id.cabinet_describe_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
//    public void changeCount(Editable s) {
//        limitCount();
//    }

    private void changeText(){
        mCabinetDescribeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                limitCount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 描述edittext限制输入个数
     */
    private void limitCount() {
        int count;
        count = mCabinetDescribeEt.getText().toString().length();
        mCountTv.setText("" + count);
        if (!(count < 10)) {
            Toast.makeText(mContext, "已到输入上限!", Toast.LENGTH_SHORT);
        }
    }

    public void setLabelText(String hint) {
        mCabinetDescribeEt.setHint(hint);
    }

    public String getLimitEditText() {
        return mCabinetDescribeEt.getText().toString();
    }
}
