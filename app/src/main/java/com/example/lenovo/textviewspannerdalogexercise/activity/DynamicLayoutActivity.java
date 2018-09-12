package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.EditView;
import com.example.lenovo.textviewspannerdalogexercise.view.LimitEditText;
import com.example.lenovo.textviewspannerdalogexercise.view.MultipleRadioView;
import com.example.lenovo.textviewspannerdalogexercise.view.YesOrNoRadioView;

import java.util.Stack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/1/30.
 */

public class DynamicLayoutActivity extends Activity {
    @Bind(R.id.add_layout_tv)
    TextView add_layout_tv;
    @Bind(R.id.delete_layout_tv)
    TextView delete_layout_tv;
    @Bind(R.id.root_layout)
    LinearLayout rootLayout;

    @Bind(R.id.show_tv)
    TextView show_tv;
    @Bind(R.id.submit_tv)
    TextView submit_tv;

    @Bind(R.id.show_iv)
    ImageView show_iv;

    private Stack<View> mStack;//栈  用加入 不用删除

    private EditView edit;
    private YesOrNoRadioView ynRbtn;
    private MultipleRadioView threeRbtn;
//    private LimitEditText limitEditText;
//    private EditView.LayoutParams edit_parent_param;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_layout);
        ButterKnife.bind(this);
        init();
//        dynamic_add();
    }

    private void init() {
        mStack = new Stack<>();
        if (threeRbtn != null) {
            threeRbtn.setChangeRbtnListener(new MultipleRadioView.ChangeRbtnListener() {
                @Override
                public void changeRbtn(RadioGroup var1, int var2) {
                    switch (var2) {
                        case R.id.isnormal_ture_rb:
                            break;
                        case R.id.isnormal_false_rb:
                            break;

                        default:
                            break;
                    }
                }
            });
        }

    }

    @OnClick({R.id.add_layout_tv, R.id.delete_layout_tv, R.id.submit_tv})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.add_layout_tv:
                dynamic_add();
                Toast.makeText(getApplicationContext(), "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_layout_tv:
                dynamic_delete();
                break;
            case R.id.submit_tv:
                showText();
                break;
            default:
                break;
        }
    }

    /**
     * 添加布局
     */
    protected void dynamic_add() {
//        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
//        RelativeLayout relativerLayout = new RelativeLayout(this);
//        Button button = new Button(this);
//        TextView textView = new TextView(this);
//
//        button.setText("button");
//        textView.setText("Some text");
//
//        LinearLayout.LayoutParams relativeLayout_parent_params
//                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        RelativeLayout.LayoutParams button_parent_params
//                = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        RelativeLayout.LayoutParams text_parent_params
//                = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        button_parent_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        text_parent_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//
//        relativerLayout.addView(button, button_parent_params);
//        relativerLayout.addView(textView, text_parent_params);
//        rootLayout.addView(relativerLayout, relativeLayout_parent_params);
//        mStack.push(relativerLayout);

        edit = new EditView(this);
        edit.setTag(1);
        EditView.LayoutParams edit_parent_params = new EditView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ynRbtn = new YesOrNoRadioView(this);
        ynRbtn.setTag(2);
        YesOrNoRadioView.LayoutParams yn_radio_parent_params = new YesOrNoRadioView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        threeRbtn = new MultipleRadioView(this);
        threeRbtn.setTag(3);
        MultipleRadioView.LayoutParams three_radio_parent_params = new MultipleRadioView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LimitEditText  limitEditText = new LimitEditText(this);
        limitEditText.setTag(4);
        LimitEditText.LayoutParams limitEditText_params = new LimitEditText.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootLayout.addView(limitEditText, limitEditText_params);

        rootLayout.addView(edit, edit_parent_params);
        rootLayout.addView(ynRbtn, yn_radio_parent_params);
        rootLayout.addView(threeRbtn, three_radio_parent_params);

        mStack.push(limitEditText);

        mStack.push(edit);
        mStack.push(ynRbtn);
        mStack.push(threeRbtn);

        limitEditText.setLabelText("请输入描述内容");
//        String name;
        edit.setLabelText("请输入", "请输入");
        ynRbtn.setLabelText("请选择", "YES", "NO");
        threeRbtn.setLabelText("请选择", "YES", "NO", "other", "no");
//        name = edit.getEditText();
//        show_tv.setText(name);

//        addLimitEditText();
    }

    /**
     * 添加带有限制的输入框
     */
    private void addLimitEditText() {
        LimitEditText  limitEditText = new LimitEditText(this);
        limitEditText.setTag(4);
        LimitEditText.LayoutParams limitEditText_params = new LimitEditText.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootLayout.addView(limitEditText, limitEditText_params);
        mStack.push(limitEditText);
        limitEditText.setLabelText("请输入描述内容");
    }

    /**
     * 移除布局
     */
    private void dynamic_delete() {
        if (mStack.size() > 0) {
            rootLayout.removeView(mStack.pop());
        }
    }

    /**
     * 显示
     */
    private void showText() {
        String s = null;
//        s += edit.getEditText() + "   ";
//        s += ynRbtn.getSelectText();
//        show_tv.setText(s);
        for (int i = 0; i < rootLayout.getChildCount(); i++) {
            int type = Integer.parseInt(rootLayout.getChildAt(i).getTag().toString());
            switch (type) {
                case 1:
                    String key1 = ((EditView) rootLayout.getChildAt(i)).getLabelName();
                    String value1 = ((EditView) rootLayout.getChildAt(i)).getEditText();
//                    String code1 = ((EditView) rootLayout.getChildAt(i)).getCode();
                    s += value1;
                    Log.e("~~~~~~" + key1, "~~~~~~" + value1);
                    break;
                case 2:
                    String key2 = ((YesOrNoRadioView) rootLayout.getChildAt(i)).getLabelName();
                    String value2 = ((YesOrNoRadioView) rootLayout.getChildAt(i)).getSelectText();
//                    String code2 = ((YesOrNoRadioView) rootLayout.getChildAt(i)).getCode();
                    s += value2;
                    Log.e("~~~~~~" + key2, "~~~~~~" + value2);
                    break;
                case 3:
                    String key3 = ((MultipleRadioView) rootLayout.getChildAt(i)).getLabelName();
                    String value3 = ((MultipleRadioView) rootLayout.getChildAt(i)).getSelectText();
//                    String code3 = ((MultipleRadioView) rootLayout.getChildAt(i)).getCode();
                    s += value3;
                    Log.e("~~~~~~" + key3, "~~~~~~" + value3);
                    break;
                case 4:
                    s += ((LimitEditText) rootLayout.getChildAt(i)).getLimitEditText().toString();
                    break;
                default:
                    break;
            }
        }
        show_tv.setText(s);
    }
}
