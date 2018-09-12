package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.TestLoadContentAdapter;
import com.example.lenovo.textviewspannerdalogexercise.adapter.TestLoadTypeAdapter;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueww on 2017/12/26.
 */

public class TestLoadViewActivity extends Activity {
    private ListView mTypeLv;
    private ListView mContentLv;
    private List<SpannerDialogBean> mTypeList = new ArrayList<SpannerDialogBean>();
    private List<SpannerDialogBean> mContentList = new ArrayList<SpannerDialogBean>();
    private List<SpannerDialogBean> mTypeContentList = new ArrayList<SpannerDialogBean>();
    private TestLoadTypeAdapter mTypeAdapter;
    private TestLoadContentAdapter mContentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_load_view);
        initView();
        init();
    }

    private void initView() {
        mTypeLv = (ListView) findViewById(R.id.type_lv);
        mContentLv = (ListView) findViewById(R.id.content_lv);
    }

    private void init() {
        setData();
        mTypeAdapter = new TestLoadTypeAdapter(this, mTypeList);
        mTypeLv.setAdapter(mTypeAdapter);
        mContentAdapter = new TestLoadContentAdapter(this, mTypeContentList);
        mContentLv.setAdapter(mContentAdapter);
        setTypeContentData(0);
        initItem();
    }

    private void initItem() {
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTypeContentData(position);
                mTypeAdapter.isSelected(position);
            }
        });
    }

    private void setTypeContentData(int position){
        mTypeContentList.clear();
        SpannerDialogBean s = new SpannerDialogBean();
        s = (SpannerDialogBean) mTypeAdapter.getItem(position);
        for (int i = 0; i < mContentList.size(); i++) {
            if (s.getText() == mContentList.get(i).getmNnum()){
                mTypeContentList.add(mContentList.get(i));
            }
        }
        mContentAdapter.setData(mTypeContentList);
    }

    private void setData() {
        mTypeList.add(new SpannerDialogBean("111", "北京美食店", "123456789"));
        mTypeList.add(new SpannerDialogBean("222", "大连美食店", "123451234"));
        mTypeList.add(new SpannerDialogBean("333", "沈阳美食店", "678956789"));
        mTypeList.add(new SpannerDialogBean("444", "营口美食店", "123456789"));
        mTypeList.add(new SpannerDialogBean("555", "鞍山美食店", "123456789"));
//        mTypeList.add(new SpannerDialogBean("666", "葫芦岛美食店", "123451234"));
//        mTypeList.add(new SpannerDialogBean("777", "铁岭美食店", "777777777"));
//        mTypeList.add(new SpannerDialogBean("888", "辽阳美食店", "888888888"));
//        mTypeList.add(new SpannerDialogBean("999", "哈尔滨美食店", "123456789"));
//        mTypeList.add(new SpannerDialogBean("101", "海南美食店", "123451234"));
//        mTypeList.add(new SpannerDialogBean("102", "杭州美食店", "777777777"));
        mTypeList.add(new SpannerDialogBean("103", "成都美食店", "888888888"));


        mContentList.add(new SpannerDialogBean("111", "北京考研", "北京美食店"));
        mContentList.add(new SpannerDialogBean("111", "北京羊蝎子", "北京美食店"));
        mContentList.add(new SpannerDialogBean("111", "北京驴打滚", "北京美食店"));
        mContentList.add(new SpannerDialogBean("111", "北京爆肚", "北京美食店"));
        mContentList.add(new SpannerDialogBean("111", "北京冰糖葫芦", "北京美食店"));
        mContentList.add(new SpannerDialogBean("222", "大连焖子", "大连美食店"));
        mContentList.add(new SpannerDialogBean("222", "大连鱿鱼", "大连美食店"));
        mContentList.add(new SpannerDialogBean("222", "大连海蛎子", "大连美食店"));
        mContentList.add(new SpannerDialogBean("333", "沈阳抻面", "沈阳美食店"));
        mContentList.add(new SpannerDialogBean("333", "沈阳冷面", "沈阳美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口河蟹", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口大米", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口烧夹子豆腐汤", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口鲅鱼饺子", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口虾爬子", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口烤鸡架", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口二高鸡排", "营口美食店"));
        mContentList.add(new SpannerDialogBean("444", "营口金刚山烤肉", "营口美食店"));
//        mContentList.add(new SpannerDialogBean("555", "鞍山", "123456789"));
//        mContentList.add(new SpannerDialogBean("666", "葫芦岛", "123451234"));
//        mContentList.add(new SpannerDialogBean("777", "铁岭", "777777777"));
//        mContentList.add(new SpannerDialogBean("888", "辽阳", "888888888"));
//        mContentList.add(new SpannerDialogBean("999", "哈尔滨", "123456789"));
//        mContentList.add(new SpannerDialogBean("101", "海南", "123451234"));
//        mContentList.add(new SpannerDialogBean("102", "杭州", "777777777"));
        mContentList.add(new SpannerDialogBean("103", "成都", "成都美食店"));
        mContentList.add(new SpannerDialogBean("103", "成都串串", "成都美食店"));
        mContentList.add(new SpannerDialogBean("103", "成都火锅", "成都美食店"));
        mContentList.add(new SpannerDialogBean("103", "成都三大炮", "成都美食店"));
    }
}
