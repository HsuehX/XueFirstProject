package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.RefreshAdapter;
import com.example.lenovo.textviewspannerdalogexercise.adapter.ShowImageAdapter;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;
import com.example.lenovo.textviewspannerdalogexercise.view.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;


/**
 * Created by xue on 2018/2/12.
 */

/**
 * 支持自定义上拉刷新界面
 */
public class RefreshListActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * recyclerview图片
     */
    private RecyclerView mRecyclerView;
    private View mView;
    private ShowImageAdapter mShowImageAdapter;
    /**
     * 每次拿到的图片url list集合
     */
    private ArrayList<String> mImageList = new ArrayList<>();
    /**
     * 一共所选择图片url list集合
     */
    private ArrayList<String> mTotalImageList = new ArrayList<>();
    public static final int REQUEST_CODE = 999;


    //    拖动加载
    private RefreshLayout swipeLayout;
    private ListView listView;
    private RefreshAdapter adapter;
    //    private ArrayList<HashMap<String, String>> list;
    private List<SpannerDialogBean> mList = new ArrayList<>();
    private View header;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list);

        initRecyPhoto();

        initView();
        setData();
        setListener();
        /*设置自动刷新 swipeLayout.setRefreshing(true);
        在 setRefreshing(true)并没有触发onRefresh的,须要手动调用一次
        */
//		swipeLayout.post(new Thread(new Runnable() {
//			@Override
//			public void run() {
//				swipeLayout.setRefreshing(true);
//			}
//		}));
//		onRefresh();
    }


    /**
     * recyclerview图片
     * <p>
     * GridLayoutManager网格布局
     * LinearLayoutManager：线性布局管理器
     * StaggeredGridLayoutManager: 错列网格布局管理器
     */
    private List<String> loadphotoList = new ArrayList();

    private void initRecyPhoto() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        mRecyclerView.setLayoutManager((new GridLayoutManager(this, 1, 1, false)));
        mRecyclerView.setLayoutManager((new LinearLayoutManager(this, 0, false)));

        loadphotoList.add(" \"drawable://\" + R.drawable.awesome; ");
        mShowImageAdapter = new ShowImageAdapter(this, loadphotoList);


//        mShowImageAdapter = new ShowImageAdapter(this);
        mRecyclerView.setAdapter(mShowImageAdapter);
        initEvebts();
    }

    /**
     * recyclerview 事件监听
     */
    private void initEvebts() {
        mShowImageAdapter.setOnPickerListener(new ShowImageAdapter.OnPickerListener() {
            @Override
            public void onPicker(int position) {
                //根据位置大小判断
                // 当前是跳转到选择图片Activity(MultiImageSelectorActivity)中还是预览Activity(PhotoPreviewActivity)中
//                if (position == mImageList.size()) {
                if (position == 0) {
//                    if (position != ShowImageAdapter.MAX_NUMBER) {
                    //此处intent中携带的参数是根据MultiImageSelectorActivity的参数互相匹配使用，个人可根据自己的项目要求进行设置
                    Intent intent = new Intent(RefreshListActivity.this, MultiImageSelectorActivity.class);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
//                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, ShowImageAdapter.MAX_NUMBER);//最大图片数
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                    if (mImageList != null && mImageList.size() > 0) {
                        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mImageList);
                    }
                    startActivityForResult(intent, REQUEST_CODE);
//                    }
                } else {
                    //跳转到图片预览界面
//                    PhotoPreviewActivity.startActivity(RefreshListActivity.this, position, mImageList);

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (RefreshListActivity.this.RESULT_OK == resultCode) {
                    //获取图片url
                    mImageList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    mTotalImageList.clear();
                    mTotalImageList.addAll(loadphotoList);
                    for (String imageUrl : mImageList) {
                        mTotalImageList.add(imageUrl);
                    }
                    mShowImageAdapter.setImageUrlList(mTotalImageList);

                }
                break;
        }
    }

    /**
     * 拖动刷新
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }

    /**
     * 添加数据
     */
    private void setData() {
//        list = new ArrayList<HashMap<String, String>>();
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("itemImage", i + "默认");
//            map.put("itemText", i + "默认");
//            list.add(map);
//        }
        mList.add(new SpannerDialogBean("111", "北京", "123456789"));
        mList.add(new SpannerDialogBean("222", "大连", "123451234"));
        mList.add(new SpannerDialogBean("333", "沈阳", "678956789"));
        mList.add(new SpannerDialogBean("444", "营口", "123456789"));
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);//这里可以不加
        adapter = new RefreshAdapter(this, mList);
        listView.setAdapter(adapter);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
//        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据  更新完后调用该方法结束刷新
//                mList.clear();

//                for (int i = 0; i < 8; i++) {
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    map.put("itemImage", i + "刷新");
//                    map.put("itemText", i + "刷新");
//                    list.add(map);
//                }
                mList.add(new SpannerDialogBean("555", "鞍山", "123456789"));
                mList.add(new SpannerDialogBean("666", "辽阳", "123451234"));
                mList.add(new SpannerDialogBean("777", "锦州", "678956789"));
                mList.add(new SpannerDialogBean("888", "葫芦岛", "123456789"));
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 2000);

    }

//    /**
//     * 加载更多
//     */
//    @Override
//    public void onLoad() {
//        swipeLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // 更新数据  更新完后调用该方法结束刷新
//                swipeLayout.setLoading(false);
//                for (int i = 1; i < 10; i++) {
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    map.put("itemImage", i + "更多");
//                    map.put("itemText", i + "更多");
//                    list.add(map);
//                }
//                adapter.notifyDataSetChanged();
//            }
//        }, 2000);
//    }

}



