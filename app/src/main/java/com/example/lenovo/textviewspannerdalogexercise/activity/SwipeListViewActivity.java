package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenu;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuCreator;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuItem;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/1/14.
 */

public class SwipeListViewActivity extends Activity {
    SwipeMenuListView listView;
    MyAdapter adapter;
//    private List<ApplicationInfo> mAppList;
    ArrayList<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu_listview);


        listView = (SwipeMenuListView) findViewById(R.id.listview);
//        mAppList = getPackageManager().getInstalledApplications(0);
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("AAA" + i);
        }
        adapter = new MyAdapter(data, this);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 0:
                        createMenu2(menu);
                        break;
                }
            }

            //设置滑出菜单的属性
            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem item2 = new SwipeMenuItem(
                        getApplicationContext());
//                item2.setBackground(R.color.allTypeItemTextColor);
                item2.setWidth(adapter.dp2px(50));
                item2.setTitle("删除");
                item2.setTitleColor(R.color.colorPrimary);
                item2.setTitleSize(15);
                menu.addMenuItem(item2);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                ApplicationInfo item = mAppList.get(position);
//                mAppList.remove(data.get(position));  //不加这里也能删除，这里有什么意义么
                adapter.delete(position);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(SwipeListViewActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public class MyAdapter extends BaseAdapter {

        ArrayList<String> data;
        Context context;

        public MyAdapter(ArrayList<String> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            MyHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_swipe, null);
                holder = new MyHolder();
                holder.tv = (TextView) view.findViewById(R.id.item_tv);
                view.setTag(holder);
            } else {
                holder = (MyHolder) view.getTag();
            }
            holder.tv.setText(data.get(position));
            return view;
        }

        class MyHolder {
            TextView tv;
        }

        public void delete(int p) {//设置删除方法
            data.remove(p);
            notifyDataSetChanged();
            listView.setSelection(p);
        }

        //设置滑动删除按钮的宽高属性的方法
        private int dp2px(int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    getResources().getDisplayMetrics());
        }
    }
}
