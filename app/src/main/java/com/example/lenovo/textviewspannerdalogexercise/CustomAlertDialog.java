package com.example.lenovo.textviewspannerdalogexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.textviewspannerdalogexercise.R;

/**
 * Created by xueww on 2017/9/16.
 */

public class CustomAlertDialog extends android.app.Dialog implements android.view.View.OnClickListener {

    private View view;
    private Button btn_no;
    private Button btn_ok;
    private EditText et;

    /**
     * 一。内部接口，要求实现一个callback方法
     * @author Administrator cuiweiyou.com
     */
    private ICallBack icb;

    /**
     * 初始化界面
     */
    private void initView (){
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_callback, null);
        et = (EditText) view.findViewById(R.id.et);
        btn_no = (Button) view.findViewById(R.id.btn_no);
        btn_no.setOnClickListener(this);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

        setContentView(view);
    }

    protected CustomAlertDialog(Context context) {
        super(context);
        initView ();
    }

    public CustomAlertDialog(Context context, boolean cancelable,
                             OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView ();
    }

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
        initView ();
    }

    /**
     * 二。自定义的构造方法，需要一个ICallBack接口
     * @param context
     * @param icb 回调器
     */
    protected CustomAlertDialog(Context context, ICallBack icb) {
        super(context);

        this.icb = icb;

        initView ();

    }

    /**
     * 三。设置回调变量
     * @param cb
     */
    // 如果不是使用上面接收icallback参数的(二)构造方法创建此自定义对话框，则需要使用此方法指定icb变量
    public void setCallBack (ICallBack cb) {
        icb = cb;
    }

    /**
     * 四。实现的监听器的方法，判断点击的控件
     * 当此自定义对话框点击按钮2时，向属性icb实例传一个参数
     * 这个参数会被界面接收使用
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_ok:
                if(icb != null){
                    // 这个方法在传入的回调器中实现
                    icb.callback(et.getText().toString());
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 一。内部接口，要求实现一个callback方法
     * @author Administrator cuiweiyou.com
     */
    interface ICallBack {
        void callback(String str);
    }

}