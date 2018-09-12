package com.example.lenovo.textviewspannerdalogexercise.view.customskidrefresh;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xue on 2018/7/30.
 */

public class CustomSkidRefreshListView extends ListView implements AbsListView.OnScrollListener {
    /**
     * listview
     */
    @Bind(R.id.lv_custom)
    ListView mCustomLv;
    /**
     * 加载框
     */
    @Bind(R.id.pgb_pull_to_refresh_load_progress)
    ProgressBar mrRefreshLoadPGB;
    /**
     * 提示文字
     */
    @Bind(R.id.tv_pull_to_refresh_loadmore_text)
    TextView mRefreshLoadmoreTv;

    private Context mContext;
    private boolean isLastItem = false;//是否是最后一项
    private int current_page = 0;//临时存放当前加载页对应的pos
    private int data_count_page_all = 0;//模拟1页数据 (上拉或点击加载更多 无更多数据)

    private boolean isLoading = false;//是否正在加载中
    private boolean isComp = false;//标记是否所有数据都加载完成


    private final String loading_Load_More = "加载中...";
    private final String comp_Load_More = "没有更多数据";

    private String nowNormalText = "";//存放当前footview显示的文字


    public CustomSkidRefreshListView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_refresh_listview, this);
        ButterKnife.bind(this, view);
//        ButterKnife.bind((Activity) context);
        init();
    }

    private void init() {
        mrRefreshLoadPGB.setVisibility(View.GONE);
        mRefreshLoadmoreTv.setText(nowNormalText);
        this.setOnScrollListener(this);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        super.setOnScrollListener(l);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //当滚到最后一行且停止滚动时(且没有加载)，执行加载
        if (isLastItem && scrollState == OnScrollListener.SCROLL_STATE_IDLE && !isLoading) {
            //加载元素
            loadMore();
            isLastItem = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断   是否滚到最后一行  是否处于正在加载的状态   是有未加载的数据
        if ((view.getLastVisiblePosition() == view.getCount() - 1 && !isLoading && !isComp) ||
                firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            isLastItem = true;
            clickLoadMore();
        }
    }


    /**
     * 加载逻辑
     */
    private void loadMore() {
        mrRefreshLoadPGB.setVisibility(View.VISIBLE);//进度框显示
        mRefreshLoadmoreTv.setText(loading_Load_More);//提示文字更改为 正在加载
        isLoading = true;//加载状态 改为正在加载
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }, 1 * 1000);
    }

    /**
     * 滑动加载更多 数据
     */
    private void loadData() {
        if (current_page < data_count_page_all) {
            current_page++;
            mRefreshLoadmoreTv.setText(nowNormalText);
            isComp = false;
            mRefreshLoadmoreTv.setClickable(true);
        } else {
            isComp = true;
            mRefreshLoadmoreTv.setText(comp_Load_More);
            mRefreshLoadmoreTv.setClickable(false);
        }
        mrRefreshLoadPGB.setVisibility(View.GONE);
    }

    /**
     * 点击加载更多
     */
    private void clickLoadMore() {
        mRefreshLoadmoreTv.setClickable(true);
        mRefreshLoadmoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMore();
            }
        });
    }

}
