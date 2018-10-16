package com.example.lenovo.textviewspannerdalogexercise.networkonuse;


import com.example.lenovo.textviewspannerdalogexercise.networkonuse.base.CustomHttpClient;
import com.example.lenovo.textviewspannerdalogexercise.utils.Constant;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetService {

    private static final int DEFAULT_TIMEOUT = 30;
    private static NetService mInstance = new NetService();
    private NetApi netApi;

    /**
     * 构造方法私有
     */
    private NetService() {
    }

    //获取单例
    public static NetService getInstance() {
        if (mInstance == null) {
            mInstance = new NetService();
        }
        return mInstance;
    }

    private synchronized NetApi getApi() {
        if (netApi != null) {
            return netApi;
        }
        OkHttpClient client = CustomHttpClient.create(DEFAULT_TIMEOUT, true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        netApi = retrofit.create(NetApi.class);
        return netApi;
    }

    /**
     * 统一的RxJava订阅处理
     *
     * @param observable
     * @param subscriber
     * @param <T>
     */
    private <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void groupChat(String user, String say, Subscriber<HashMap<String, String>> subscriber) {
        Observable<HashMap<String, String>> observable = getApi().groupChat(user, say);
        subscribe(observable, subscriber);
    }

//    public void login(String usernName, String enPwd, Subscriber<BeanResLogin> subscriber) {
//        Observable<BeanResLogin> observable = getApi().login(usernName, enPwd);
//        subscribe(observable, subscriber);
//    }
//
//
//    /**
//     * 上传任务
//     */
//    public void uploadTask(BeanReqUploadTask req, Subscriber<BeanRespCommon> subscriber) {
//        SLog.netLog("upLoadTask: "+ JsonUtils.toJson(req));
//        Observable<BeanRespCommon> observable = getApi().taskUpLoad(req);
//        subscribe(observable, subscriber);
//    }
//
//
//    /**
//     * 测试 告警上报接口
//     */
//    public void reqAlarmWarning(BeanReqAlarmInformation bean,Subscriber<BeanRespCommon> subscriber) {
//        Observable<BeanRespCommon> observable = getApi().reqWarning(bean);
//        subscribe(observable, subscriber);
//    }
//
//
//    /**
//     * 测试 巡检感应点信息上报
//     */
//    public void reqTaskRf(String taskCode, String urmpCode, String state, Subscriber<BeanRespCommon> subscriber) {
//        Observable<BeanRespCommon> observable = getApi().reqTaskRf(taskCode,urmpCode,state);
//        subscribe(observable, subscriber);
//    }
//
////    /**
////     * 测试 状态：未执行/执行中/已完成)
////     */
////    public void reqLogin(Subscriber<BaseResp<String>> subscriber) {
////        Observable<BaseResp<String>> observable = getApi().testMobileLogin();
////        subscribe(observable, subscriber);
////    }
//
//    /**
//     * 测试 手机端登出接口
//     */
//    public void reqLogout(Subscriber<BeanRespCommon> subscriber) {
//        Observable<BeanRespCommon> observable = getApi().mobileLogout();
//        subscribe(observable, subscriber);
//    }
//
//    /**
//     * 上传
//     */
//    public void uploadPic(MultipartBody.Part file , Subscriber<BeanRespUploadPic> subscriber) {
//        Observable<BeanRespUploadPic> observable = getApi().uploadPic(file);
//        subscribe(observable, subscriber);
//    }
//
//
//    /**
//     * 图片下载
//     */
//    public void downloadPic(String fileUrl , Subscriber<ResponseBody> subscriber) {
//        Observable<ResponseBody> observable = getApi().downloadPicFromNet(fileUrl);
//        subscribe(observable, subscriber);
//    }
//
//    public void getBaseData(  Subscriber<List<BaseDataDto>> subscriber) {
//        Observable<List<BaseDataDto>> observable = getApi().getBaseData();
//        subscribe(observable, subscriber);
//    }
}
