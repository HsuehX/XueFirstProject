package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.base.BaseActivity;
import com.example.lenovo.textviewspannerdalogexercise.view.whellviewda.ButtomDialogView;
import com.example.lenovo.textviewspannerdalogexercise.view.whellviewda.bean.ChildBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xue on 2018/8/1.
 */

public class WheelViewDaActivity extends BaseActivity {
    private TextView mShowTv;
    private static final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};

    private List<String> parentList = new ArrayList<>();
    private List<ChildBean> childList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheelview);
        falseData();

        mShowTv = (TextView) findViewById(R.id.tv_show);
        findViewById(R.id.main_show_dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final ButtomDialogView dialog = new ButtomDialogView(MainActivity.this, PLANETS);

                final ButtomDialogView dialog = new ButtomDialogView(WheelViewDaActivity.this, parentList, childList);

                dialog.show();
                dialog.setOnConfirmClickListener(new ButtomDialogView.ConfirmClickListener() {
                    @Override
                    public void onConfirmClickListener(String wv, String wv1) {
                        mShowTv.setText(wv + "              " + wv1);
                    }
                });
            }
        });
    }

    private void falseData() {
        parentList = Arrays.asList(PLANETS);
        for (int i = 0; i < parentList.size(); i++) {
            childList.add(new ChildBean(parentList.get(i), i + parentList.get(i)));
            childList.add(new ChildBean(parentList.get(i), i + 1 + parentList.get(i)));
        }
    }
}
