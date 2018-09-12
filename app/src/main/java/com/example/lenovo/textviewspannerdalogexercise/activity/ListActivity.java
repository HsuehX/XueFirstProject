package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.ListAdapter;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xueww on 2017/12/25.
 */

public class ListActivity extends Activity {
    /**
     * 全选
     */
    @Bind(R.id.check_all_cb)
    public CheckBox mCbCheckAll;

    @Bind(R.id.lv_notice)
    public ListView mShowLv;

    private List<SpannerDialogBean> mList = new ArrayList<SpannerDialogBean>();
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        mList.add(new SpannerDialogBean("111", "北京", "123456789"));
        mList.add(new SpannerDialogBean("222", "大连", "123451234"));
        mList.add(new SpannerDialogBean("333", "沈阳", "678956789"));
        mList.add(new SpannerDialogBean("444", "营口", "123456789"));
        mList.add(new SpannerDialogBean("555", "鞍山", "123456789"));
        mList.add(new SpannerDialogBean("666", "葫芦岛", "123451234"));
        mList.add(new SpannerDialogBean("777", "铁岭", "777777777"));
        mList.add(new SpannerDialogBean("888", "辽阳", "888888888"));
        mList.add(new SpannerDialogBean("999", "哈尔滨", "123456789"));
        mList.add(new SpannerDialogBean("101", "海南", "123451234"));
        mList.add(new SpannerDialogBean("102", "杭州", "777777777"));
        mList.add(new SpannerDialogBean("103", "成都", "888888888"));
        mAdapter = new ListAdapter(ListActivity.this, mList);
        mShowLv.setAdapter(mAdapter);
    }

    /**
     * 设置全选按钮为选择状态
     */
    public void setCheckAllCb() {
        mCbCheckAll.setChecked(true);
    }

    /**
     * 设置全选按钮为未选择状态
     */
    public void cancelCheckAllCb() {
        mCbCheckAll.setChecked(false);
    }
}
