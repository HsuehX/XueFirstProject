package com.example.lenovo.textviewspannerdalogexercise.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.QQListView;

/**
 * Created by xue on 2018/1/19.
 */

public class CopyQQListViewActivity extends Activity {
    private QQListView mListView;
    private ArrayList<String> mData = new ArrayList<String>() {
        {
            for (int i = 0; i < 50; i++) {
                add("hello world, hello android  " + i);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_qq_list_delete);

        mListView = (QQListView) findViewById(R.id.list);
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (mListView.canClick()) {
                    Toast.makeText(CopyQQListViewActivity.this, mData.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(CopyQQListViewActivity.this, R.layout.item_copy_qq_list_delete, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            TextView delete = (TextView) convertView.findViewById(R.id.delete);

            tv.setText(mData.get(position));

            final int pos = position;
            delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(pos);
                    notifyDataSetChanged();
                    mListView.turnToNormal();
                }
            });

            return convertView;
        }
    }
}