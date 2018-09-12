package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.Service.SwipeDataService;
import com.example.lenovo.textviewspannerdalogexercise.adapter.SwipeDataAdapter;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.DemoDto;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.ShoppingCarDto;
import com.example.lenovo.textviewspannerdalogexercise.utils.Session;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenu;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuCreator;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuItem;
import com.example.lenovo.textviewspannerdalogexercise.view.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by xue on 2018/1/15.
 */

public class SwipeListViewDataActivity extends Activity {
    @Bind(R.id.account_number_et)
    EditText mAccountNumberEt;
    @Bind(R.id.title_et)
    EditText mTitleEt;
    @Bind(R.id.date_et)
    EditText mDateEt;
    @Bind(R.id.add_btn)
    Button mAddBtn;
    @Bind(R.id.data_listview)
    SwipeMenuListView mDataSwipeListview;
    @Bind(R.id.show_data_tv)
    TextView mShowDataTv;

    private SwipeDataService mSwipeDataService;
    private SwipeDataAdapter mSwipeDataAdapter;
    private List<DemoDto> mList = new ArrayList<DemoDto>();

    private static int ON_TOP = 1;
    private static int CANCEL_TOP = 0;
    public static String TOP_STATES = "TOP";
//    private List<Session> sessionList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_listview_data);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        mSwipeDataService = new SwipeDataService(this);
        mSwipeDataAdapter = new SwipeDataAdapter(this, mList);
        mDataSwipeListview.setAdapter(mSwipeDataAdapter);
        refreshView();
    }

    @OnClick({R.id.add_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                setData();
                loadData();
                break;
            default:
                break;
        }
    }

    @OnItemClick(R.id.data_listview)
    public void itemOnClick(AdapterView<?> parent, View view, final int position, long id) {
        Toast.makeText(getApplication(), mList.get(position) + "", Toast.LENGTH_SHORT);
        mAccountNumberEt.setText(mSwipeDataAdapter.getItem(position).getTopTime() + "");
    }

    private void loadData() {
        String s = "";
        List<DemoDto> list = new ArrayList<DemoDto>();
        list = mSwipeDataService.queryForAll();
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i).getCourseTitle() + "\n";
        }
        mShowDataTv.setText(s);
        setSwipeListData();
    }

    private void setData() {
        String number = String.valueOf(mAccountNumberEt.getText());
        String title = String.valueOf(mTitleEt.getText());
        String date = String.valueOf(mDateEt.getText());
        DemoDto dao = new DemoDto(number, title, date);
        mSwipeDataService.addDemoDto(dao);
    }


    /**
     * 设置侧滑listview的数据
     */
    private void setSwipeListData() {
        mList = mSwipeDataService.queryForAll();
//        mSwipeDataAdapter.setData(mList);
        mSwipeDataAdapter.notifyDataSetChanged();
        SwipeClick();
        refreshView();
    }

    /**
     * 侧滑点击事件
     */
    private void SwipeClick() {
        //根据不同的视图类型创建不同的菜单
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 0:
                        createMenu2(menu);
                        break;
                    default:
                        break;
                }
            }
        };
        mDataSwipeListview.setMenuCreator(creator);//创建滑动菜单
        //滑动菜单中item点击事件
        mDataSwipeListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                mSwipeDataAdapter
                switch (index) {
                    case 0:
//                        final Session session = (Session) parent.getItemAtPosition(position);
                        final Session session = (Session) mList.get(position);
                        session.setTop(ON_TOP);
                        session.setTopTime(System.currentTimeMillis());
                        refreshView();
                        break;
                    case 1:
                        // delete
                        mList.remove(position);
                        mSwipeDataAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        // delete
                        mSwipeDataAdapter.delete(mList.get(position).getCourseID());
                        mList = mSwipeDataService.queryForAll();
                        mSwipeDataAdapter.setData(mList);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 设置滑出菜单的属性
     */
    private void createMenu2(SwipeMenu menu) {
        SwipeMenuItem openItem = new SwipeMenuItem(
                getApplicationContext());
        openItem.setBackground(new ColorDrawable(Color.LTGRAY));
        openItem.setWidth(mSwipeDataAdapter.dp2px(60));
        openItem.setTitle("置顶");
        openItem.setTitleSize(15);
        openItem.setTitleColor(Color.WHITE);
        menu.addMenuItem(openItem);

        SwipeMenuItem NoopenItem = new SwipeMenuItem(
                getApplicationContext());
        NoopenItem.setBackground(new ColorDrawable(Color.CYAN));
        NoopenItem.setWidth(mSwipeDataAdapter.dp2px(90));
        NoopenItem.setTitle("删除(只在列表)");
        NoopenItem.setTitleSize(15);
        NoopenItem.setTitleColor(Color.WHITE);
        menu.addMenuItem(NoopenItem);

        SwipeMenuItem deleteItem = new SwipeMenuItem(
                getApplicationContext());
        deleteItem.setBackground(new ColorDrawable(Color.RED));
        deleteItem.setWidth(mSwipeDataAdapter.dp2px(60));
        deleteItem.setTitle("删除");
        deleteItem.setIcon(R.drawable.ic_delete);
        deleteItem.setTitleSize(15);
        deleteItem.setTitleColor(Color.WHITE);
        menu.addMenuItem(deleteItem);
    }

    private void refreshView() {
        //如果不调用sort方法，是不会进行排序的，也就不会调用compareTo
        Collections.sort(mList);
//        mSwipeDataAdapter.updateData(sessionList);
//        mSwipeDataAdapter.notifyDataSetChanged();
        mSwipeDataAdapter.setData(mList);

    }

}
