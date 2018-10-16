package com.example.lenovo.textviewspannerdalogexercise.activity.internet;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.internet.adapter.SimpleRetrofitRecylerAdapter;
import com.example.lenovo.textviewspannerdalogexercise.activity.internet.helper.SwipeToLoadHelper;
import com.example.lenovo.textviewspannerdalogexercise.activity.internet.view.adapter.RecylerViewAdapterWrapper;
import com.example.lenovo.textviewspannerdalogexercise.base.BaseActivity;
import com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.SimpleRetrofitAPI;
import com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean.BeanSimpleRetrofit;
import com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean.BeanSimpleRetrofitResp;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xue on 2018/8/27.
 */

public class SimpleRetrofitActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeToLoadHelper.LoadMoreListener {

    @Bind(R.id.gank_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.gank_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.gank_loading)
    ProgressBar mProgressBar;

    @Bind(R.id.gank_load_failed_tv)
    TextView mTvLoadFailed;

    private RecyclerView.LayoutManager mLayoutManager;

    private List<BeanSimpleRetrofit> mListData = new ArrayList<>();
    private String mType;
    private int mCurrentPage;
    private SimpleRetrofitAPI api;
    //    private SimpleRetrofitRecylerAdapter adapter;
    private SwipeToLoadHelper mLoadMoreHelper;
    private RecylerViewAdapterWrapper mAdapterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_retrofit);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mType = "Android";
        mCurrentPage = 1;
        initView();
//        adapter = new SimpleRetrofitRecylerAdapter(this, mListData, mType);
//        mRecyclerView.setAdapter(adapter);


        //这个部分没改完  不知道什么情况
        mAdapterWrapper = new RecylerViewAdapterWrapper(new SimpleRetrofitRecylerAdapter(this, mListData, "Android"));
        mLoadMoreHelper = new SwipeToLoadHelper(mRecyclerView, mAdapterWrapper);
        mLoadMoreHelper.setLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapterWrapper);


        initNetworkRq();
//        loadData(0, mType, 20, mCurrentPage);
        initLoad();
    }

    private void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        if ("Android".equals(mType)) {
            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        } else {
            mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);//设置方向 等属性
    }


    /**
     * 初始化网络请求相关
     */
    private void initNetworkRq() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SimpleRetrofitAPI.BASEURL)
                .client(client)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(SimpleRetrofitAPI.class);
    }


    /**
     * @param requestDataType 请求类型
     *                        0 : 初始化请求
     *                        1 : refresh请求
     *                        2 : loadMore请求
     * @param mType
     * @param countPerPage
     * @param mCurrentPage
     */
    public void loadData(final int requestDataType, String mType, int countPerPage, int mCurrentPage) {
        api.requestData(mType, countPerPage, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeanSimpleRetrofitResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BeanSimpleRetrofitResp value) {
                        switch (requestDataType) {
                            //加载
                            case 0:
                                mListData.clear();
                                mListData.addAll(value.getmResults());
                                mAdapterWrapper.notifyDataSetChanged();
                                break;
                            //刷新
                            case 1:
                                mListData.clear();
                                mListData.addAll(value.getmResults());
                                mAdapterWrapper.notifyDataSetChanged();
                                refreshComplete();
                                break;
                            //加载更多
                            case 2:
                                mListData.addAll(value.getmResults());
                                mAdapterWrapper.notifyDataSetChanged();
                                loadMoreComplete();
                                break;

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        initLoadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 重写SwipeRefreshLayout中的方法（继承接口）
     */
    @Override
    public void onRefresh() {
        // 刷新时禁用上拉加载更多
        mLoadMoreHelper.setSwipeToLoadEnabled(false);
        refresh();
    }


    /**
     * 重写SwipeToLoadHelper中方法（继承接口）
     */
    @Override
    public void onLoadMore() {
        mSwipeRefreshLayout.setEnabled(false);
        loadMore();
    }

    /**
     * 请求参数：requestDataType
     * 0：加载
     * 1：刷新
     * 2：加载更多
     */
    public void initLoad() {
        mCurrentPage = 1;
        loadData(0, mType, 20, 1);
    }

    public void refresh() {
        mCurrentPage = 1;
        loadData(1, mType, 20, 1);
    }

    public void loadMore() {
        mCurrentPage++;
        loadData(2, mType, 20, 1);
    }

    public void refreshComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
        // 刷新完成是解禁上拉加载更多
        mLoadMoreHelper.setSwipeToLoadEnabled(true);
        mAdapterWrapper.notifyDataSetChanged();
    }

    /***
     *  刷新界面显示 并且解禁SwipeRefresh功能
     */
    public void loadMoreComplete() {
        mSwipeRefreshLayout.setEnabled(true);
        mLoadMoreHelper.setLoadMoreFinish();
        mAdapterWrapper.notifyDataSetChanged();
    }

    public void initLoadFailed() {
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mTvLoadFailed.setVisibility(View.VISIBLE);
    }
}
