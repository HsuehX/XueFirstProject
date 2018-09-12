package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.example.lenovo.textviewspannerdalogexercise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueww on 2017/12/8.
 */

public class ArrayAdapterActivity extends Activity {
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            mList.add("asd" + i);
        }
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.item_array_adapter, R.id.test, mList);

    }
}
