package com.example.lenovo.textviewspannerdalogexercise.activity.internet.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.textviewspannerdalogexercise.activity.internet.view.adapter.RecylerViewAdapterWrapper;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by xue on 2018/9/3.
 * 上滑加载更多操作辅助类
 */

public class SwipeToLoadHelper extends RecyclerView.OnScrollListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecylerViewAdapterWrapper mWrapper;
    private LoadMoreListener moreListener;
    private boolean isLoading = false;//是否正在加载中
    private boolean isSwipeToLoadEnabled = true;//上拉刷新功能是否开启


    /**
     * instanceof 判断其左边对象是否为其右边类的实例
     */
    public SwipeToLoadHelper(RecyclerView recyclerView, RecylerViewAdapterWrapper wrapper) {
        mLayoutManager = recyclerView.getLayoutManager();//recyclerView的线性布局
        mWrapper = wrapper;

        //设置控件布局
        if (mLayoutManager instanceof GridLayoutManager) {//如果属于GridView
            mWrapper.setAdapterType(RecylerViewAdapterWrapper.ADAPTER_TYPE_GRID);//网格
            mWrapper.setSpanCount(((GridLayoutManager) mLayoutManager).getSpanCount());//列数
        } else if (mLayoutManager instanceof LinearLayoutManager) {//如果属于LinearLayout
            mWrapper.setAdapterType(RecylerViewAdapterWrapper.ADAPTER_TYPE_LINEAR);
        }

        // 将OnScrollListener设置RecyclerView
        recyclerView.addOnScrollListener(this);
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //上拉刷新功能开启，且不再滚动中，且不处于正在加载中
        if (isSwipeToLoadEnabled && SCROLL_STATE_IDLE == newState && !isLoading) {
            //网格
            if (mLayoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (isSwipeToLoadEnabled) {
                            // 功能开启, 根据位置判断, 最后一个item时返回整个宽度, 其他位置返回1
                            // AdapterWrapper会保证最后一个item会从新的一行开始
                            if (position == mLayoutManager.getItemCount() - 1) {
                                return gridLayoutManager.getSpanCount();
                            } else {
                                return 1;
                            }
                        } else {
                            return 1;
                        }
                    }
                });

            }

            //线性
            if (mLayoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mLayoutManager;
                int lastCompletePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();//返回的是最后一个视图的位置
                //当倒数第二项是完整显示的，（[最后一行]倒数一项不是完整显示）
                if (lastCompletePosition == mLayoutManager.getItemCount() - 2) {
                    int firstCompletePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    View chlid = linearLayoutManager.findViewByPosition(lastCompletePosition);
                    if (chlid == null) {
                        return;
                    }
                    //view.getBottom()表示的是view底部以父View的左上角为原点的垂直坐标位置 ;
                    int deltaY = recyclerView.getBottom() - recyclerView.getPaddingBottom() - chlid.getBottom();
                    if (deltaY > 0 && firstCompletePosition != 0) {
                        recyclerView.smoothScrollBy(0, -deltaY);
                    }

                } else if (lastCompletePosition == mLayoutManager.getItemCount() - 1) {
                    // 最后一项完全显示, 触发操作, 执行加载更多操作 禁用回弹判断
                    isLoading = true;
                    mWrapper.setLoadItemState(true);
                    if (moreListener != null) {
                        moreListener.onLoadMore();
                    }
                }
            }
        }
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    /**
     * 设置下拉刷新功能是否开启
     */
    public void setSwipeToLoadEnabled(boolean swipeToLoadEnabled) {
        if (isSwipeToLoadEnabled != swipeToLoadEnabled) {
            isSwipeToLoadEnabled = swipeToLoadEnabled;
            mWrapper.setLoadItemVisibility(swipeToLoadEnabled);
        }
    }

    /**
     * 设置LoadMore Item为加载完成状态, 上拉加载更多完成时调用
     */
    public void setLoadMoreFinish() {
        isLoading = false;
        mWrapper.setLoadItemState(false);
    }

    /**
     * 上拉操作触发时调用的接口
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        moreListener = loadMoreListener;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }

}
