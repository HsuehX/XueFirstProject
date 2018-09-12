package com.example.lenovo.textviewspannerdalogexercise.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;


/**
 * Created by xueww on 2017/9/7.
 */

public class PCCodeDialog extends Dialog {
    private Context mContext;
    private EditText mPickUpNumOneEt;
    private EditText mPickUpNumTwoEt;
    private EditText mPickUpNumThreeEt;
    private EditText mPickUpNumFourEt;
    private EditText mPickUpNumFiveEt;
    private EditText mPickUpNumSixEt;
    private ClickListenerInterface clickListenerInterface;

    public PCCodeDialog(Context context) {
        super(context, R.style.common_dialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.dialog_pickup_code);


        ImageView CloseTv = (ImageView) findViewById(R.id.iv_back);
        CloseTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        mPickUpNumOneEt = (EditText) findViewById(R.id.code_one_et);
        mPickUpNumTwoEt = (EditText) findViewById(R.id.code_two_et);
        mPickUpNumThreeEt = (EditText) findViewById(R.id.code_three_et);
        mPickUpNumFourEt = (EditText) findViewById(R.id.code_four_et);
        mPickUpNumFiveEt = (EditText) findViewById(R.id.code_five_et);
        mPickUpNumSixEt = (EditText) findViewById(R.id.code_six_et);
    }

    private void initData() {
        String one = mPickUpNumOneEt.getText().toString();
        String two = mPickUpNumTwoEt.getText().toString();
        String three = mPickUpNumThreeEt.getText().toString();
        String four = mPickUpNumFourEt.getText().toString();
        String five = mPickUpNumFiveEt.getText().toString();
        String six = mPickUpNumSixEt.getText().toString();
        String returnNumber = one + two + three + four + five + six;
        mPickUpNumSixEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
    }

    public interface ClickListenerInterface {

        public void doConfirm();

        public void doCancel();
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.code_six_et:
                    clickListenerInterface.doConfirm();
                    break;
//                case R.id.dialog_yesorno_no_bt:
//                    clickListenerInterface.doCancel();
//                    break;
                default:
                    break;
            }
        }

    };

    private class onEditorActionListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            return false;
        }
    }
}
