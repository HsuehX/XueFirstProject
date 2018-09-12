package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.EvaluateSatisfactionLevelAdapter;
import com.example.lenovo.textviewspannerdalogexercise.view.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/8/15.
 */

public class EvaluationActivity extends Activity {

    //    @BindView(R.id.lv_satisfaction_level)
//    ListView mSatisfactionLevelLv;
    private CustomListView mSatisfactionLevelLv;

    private List<String> satisfactionLevelList = new ArrayList<>();
    private EvaluateSatisfactionLevelAdapter evaluateSatisfactionLevelAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_wm_order);
        init();
    }

    private void init() {
        mSatisfactionLevelLv = (CustomListView) findViewById(R.id.lv_satisfaction_level);
        falseData();
        evaluateSatisfactionLevelAdapter = new EvaluateSatisfactionLevelAdapter(this, satisfactionLevelList);
        mSatisfactionLevelLv.setAdapter(evaluateSatisfactionLevelAdapter);
        mSatisfactionLevelLv.setListViewHeight(800);
    }

    private void falseData() {
        satisfactionLevelList.add("私房豆腐");
        satisfactionLevelList.add("爆炒鸡肝(小份)");
        satisfactionLevelList.add("小厨酱茄条");
    }
}
