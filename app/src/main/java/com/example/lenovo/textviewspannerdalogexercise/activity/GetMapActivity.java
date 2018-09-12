package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by xueww on 2017/12/20.
 */

public class GetMapActivity extends Activity {
    private TextView mShowMapTv;
    private List<Map<String, String>> mMapList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmap);
        init();
    }

    private void init() {
        mShowMapTv = (TextView) findViewById(R.id.show_map_tv);
        mMapList = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "11111");
        map.put("2", "22222");
        map.put("3", "33333");
        map.put("4", "44444");
        map.put("5", "55555");
        map.put("2", "valu");
        mMapList.add(map);
        for (Map<String, String> m : mMapList) {
            for (String k : m.keySet()) {
                System.out.println(k + " : +++++++++++++++" + m.get(k));
                mShowMapTv.setText(map.get(k));
            }
        }

//        for (int i = 0; i < mMapList.size(); i++) {
//            for (int j = 0; j < map.size(); j++) {
//                mShowMapTv.setText(map.get(i));
//            }
//            for (String test:map) {
//                System.out.println(test+","+map.get(test));
//            }
//        }
    }

}
