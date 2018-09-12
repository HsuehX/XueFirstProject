package com.example.lenovo.textviewspannerdalogexercise.view.whellviewda;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.whellviewda.bean.ChildBean;

import java.util.ArrayList;
import java.util.List;

public class ButtomDialogView extends Dialog {

    private Context context;
    String[] PLANETS;
    private List<String> parentList = new ArrayList<>();
    private List<ChildBean> childList = new ArrayList<>();
    private int selectParentIndex = 0;

    private ConfirmClickListener confirmClickListener;
    private WheelViewDa wv;
    private WheelViewDa wv1;


    public void setOnConfirmClickListener(ConfirmClickListener listener) {
        confirmClickListener = listener;
    }

    public ButtomDialogView(Context context, String[] PLANETS) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.PLANETS = PLANETS;
    }

    public ButtomDialogView(Context context, List<String> parentList, List<ChildBean> childList) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.parentList = parentList;
        this.childList = childList;
    }

    public ButtomDialogView(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    //    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(view);//这行一定要写在前面
//        setCancelable(iscancelable);//点击外部不可dismiss

        setContentView(R.layout.wheel_view);
        setCanceledOnTouchOutside(false);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        wv = (WheelViewDa) findViewById(R.id.wheel_view_wv);
        wv1 = (WheelViewDa) findViewById(R.id.wheel_view_wv1);


        final int w = getScreenWidth();
//        wv.setItems(Arrays.asList(PLANETS));
        wv.setItems(parentList);

        ViewGroup.LayoutParams param = wv.getLayoutParams();
        param.width = w / 3;
        wv.setLayoutParams(param);

        ViewGroup.LayoutParams param1 = wv1.getLayoutParams();
        param1.width = w / 3;
        wv1.setLayoutParams(param);
        setChild();


        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        TextView tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                if (confirmClickListener != null) {
                    confirmClickListener.onConfirmClickListener(wv.getSeletedIndex() + "", wv1.getSeletedIndex() + "");
                }
            }
        });


        wv.setOnSlidingChangeListener(new WheelViewDa.SlidingChangeListener() {
            @Override
            public void onSlidingChangeListener(int parentVaule) {
                selectParentIndex = parentVaule;
                setChild();
            }
        });
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public interface ConfirmClickListener {
        void onConfirmClickListener(String wv, String wv1);
    }


    /**
     * 子级
     */
    private void setChild() {
        wv1.setItems(setChildData());
    }

    /**
     * 子级数据
     */
    private List<String> setChildData() {
        List<String> child = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            if (!(i < childList.size()) || !(selectParentIndex < parentList.size())) {
                break;
            } else if (!TextUtils.isEmpty(parentList.get(selectParentIndex))
                    && parentList.get(selectParentIndex).equals(childList.get(i).getParentValue())) {
                child.add(childList.get(i).getChildVaule());
            }
        }
        return child;
    }


    /**
     * 下按摩的没用到  不删了 手势相关的
     */
    private float X1;
    private float X2;
    private float Y1;
    private float Y2;
    private float ComparedX;
    private float ComparedY;

    public void slidingDirection(float X1, float X2, float Y1, float Y2) {
        ComparedX = X2 - X1;//第二次的X坐标的位置减去第一次X坐标的位置，代表X坐标上的变化情况
        ComparedY = Y2 - Y1;//同理
        //当X坐标的变化量的绝对值大于Y坐标的变化量的绝对值，以X坐标的变化情况作为判断依据
        //上下左右的判断，都在一条直线上，但手指的操作不可能划直线，所有选择变化量大的方向上的量
        //作为判断依据
        if (Math.abs(ComparedX) >= Math.abs(ComparedY)) {
            //leader X
            if (ComparedX > 0) {
                //手机屏幕的坐标值，从左上方到右下方增加，横为X轴，竖为Y轴
                Toast.makeText(context, "向右滑动", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "向左滑动", Toast.LENGTH_SHORT).show();
            }
        } else {
            // leader Y
            if (ComparedY > 0) {
                Toast.makeText(context, "向下滑动", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "向上滑动", Toast.LENGTH_SHORT).show();
            }
        }

    }
}