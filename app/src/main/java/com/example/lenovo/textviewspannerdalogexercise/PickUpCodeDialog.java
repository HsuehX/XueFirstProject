package com.example.lenovo.textviewspannerdalogexercise;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by xueww on 2017/9/7.
 */

public class PickUpCodeDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private EditText mPickUpNumOneEt;
    private EditText mPickUpNumTwoEt;
    private EditText mPickUpNumThreeEt;
    private EditText mPickUpNumFourEt;
    private EditText mPickUpNumFiveEt;
    private EditText mPickUpNumSixEt;
    private String returnNumber;
//    private DeliveryBatchService mDeliveryBatchService;
    private Toast mToast = null;
//    private LoadProgressDialog mWaitingDialog = null;
    private TextView mRepeatPickUpNumTv;//重发取货码

    private ICallBack mIcb;//回调

    public PickUpCodeDialog(Context context) {
        super(context, R.style.common_dialog);
        this.mContext = context;
    }


    /**
     * ICallBack接口
     * @param context
     * @param icb 回调器
     */
    protected PickUpCodeDialog(Context context, ICallBack icb) {
        super(context, R.style.common_dialog);
        this.mContext = context;
        this.mIcb = icb;
//        initView ();
//        initData();
    }

    /**
     * 设置回调变量
     * @param cb
     */
    public void setCallBack (ICallBack cb) {
        mIcb = cb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
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
        mRepeatPickUpNumTv = (TextView) findViewById(R.id.repeat_tv);
        mRepeatPickUpNumTv.setOnClickListener(this);
    }

    private void initData() {
//        mDeliveryBatchService = new DeliveryBatchService(getContext());
        edittextChange();
        //软键盘回车
        mPickUpNumSixEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                queryBatch(returnNumber);
                if(mIcb != null){
                    final String one = mPickUpNumOneEt.getText().toString();
                    String two = mPickUpNumTwoEt.getText().toString();
                    String three = mPickUpNumThreeEt.getText().toString();
                    String four = mPickUpNumFourEt.getText().toString();
                    String five = mPickUpNumFiveEt.getText().toString();
                    String six = mPickUpNumSixEt.getText().toString();
                    returnNumber = one + two + three + four + five + six;
                    // 这个方法在传入的回调器中实现
                    mIcb.callback(returnNumber);
                }
                cancel();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repeat_tv:
                break;
            default:
                break;
        }
    }

    /**
     * 输入框输入一个数字之后自动跳到下一个输入框
     **/
    private void edittextChange() {
        TextWatcher tw = new TextWatcher() {
            //@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            //@Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //@Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    if (mPickUpNumOneEt.isFocused()) {
                        mPickUpNumOneEt.clearFocus();
                        mPickUpNumTwoEt.requestFocus();
                    } else if (mPickUpNumTwoEt.isFocused()) {
                        mPickUpNumTwoEt.clearFocus();
                        mPickUpNumThreeEt.requestFocus();
                    } else if (mPickUpNumThreeEt.isFocused()) {
                        mPickUpNumThreeEt.clearFocus();
                        mPickUpNumFourEt.requestFocus();
                    } else if (mPickUpNumFourEt.isFocused()) {
                        mPickUpNumFourEt.clearFocus();
                        mPickUpNumFiveEt.requestFocus();
                    } else if (mPickUpNumFiveEt.isFocused()) {
                        mPickUpNumFiveEt.clearFocus();
                        mPickUpNumSixEt.requestFocus();
                    }
                }
            }
        };
        mPickUpNumOneEt.addTextChangedListener(tw);
        mPickUpNumTwoEt.addTextChangedListener(tw);
        mPickUpNumThreeEt.addTextChangedListener(tw);
        mPickUpNumFourEt.addTextChangedListener(tw);
        mPickUpNumFiveEt.addTextChangedListener(tw);
        mPickUpNumSixEt.addTextChangedListener(tw);
    }

//    /**
//     * 取货码验证
//     */
//    public void queryBatch(String mailNo) {
//        BeanReqValiDateVerificationCode beanReqValiDateVerificationCode = new BeanReqValiDateVerificationCode();
//        beanReqValiDateVerificationCode.setWaybillNo(mailNo);
//        mDeliveryBatchService.checkPickupCode(beanReqValiDateVerificationCode, new ViewCallback() {
//            @Override
//            public void returnResult(boolean result, Object... objects) {
//                if (result) {
//                    dismissWaitingDialog();
//                    showToast(objects[0].toString());
//                } else {
//                    dismissWaitingDialog();
////                    SoundControl.getInstance().playSound(SoundControl.WARNING_SOUND);
//                    showToast(objects[0].toString());
//                }
//            }
//        });
//    }

//    /**
//     * Toast提示框
//     **/
//    protected void showToast(String msg) {
//        if (mToast == null) {
//            mToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
//        } else {
//            mToast.setText(msg);
//        }
//        mToast.show();
//    }
//
//    /**
//     * 显示等待对话框
//     *
//     * @return
//     */
//    public void showWaitingDialog(final String content) {
//        if (mWaitingDialog != null) {
//            mWaitingDialog.dismiss();
//            mWaitingDialog = null;
//        }
//        mWaitingDialog = new LoadProgressDialog(getContext());
//        mWaitingDialog.setMessage(content);
//        mWaitingDialog.setCanceledOnTouchOutside(false);
//        mWaitingDialog.setCancelable(false);
//        mWaitingDialog.show();
//    }
//
//    /**
//     * 隐藏进度对话框
//     */
//    public void dismissWaitingDialog() {
//        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
//            mWaitingDialog.dismiss();
//            mWaitingDialog = null;
//        }
//    }

//    public void setReturnNumber(String returnNumber) {
//        this.returnNumber = returnNumber;
//    }

    public String getReturnNumber() {
        return returnNumber;
    }


    /**
     * 一。内部接口，要求实现一个callback方法
     */
    interface ICallBack {
        void callback(String str);
    }
}
