package com.example.lenovo.textviewspannerdalogexercise.networkonuse;


import com.example.lenovo.textviewspannerdalogexercise.utils.Constant;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface NetApi {
    String HEADER_ACCEPT = "Accept: application/json; charset=UTF-8";
    String HEADER_IMG_ACCEPT = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    String HEADER_CONTENTTYPE = "Content-Type: application/json; charset=UTF-8";
    String HEADER_CONNECTION = "Connection: close";

    /**
     * 聊天群聊接口
     */
    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
    @GET(Constant.SERVER_URL + "pushVideoListToWeb")
    Observable<HashMap<String, String>> groupChat(@Query("user") String user, @Query("say") String object);

//    /**
//     * 登录接口
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @GET(Constant.SERVER_URL + "api/login/mobile/{username}")
//    Observable<BeanResLogin> login(@Path("username") String userName, @Query("userPassword") String encryptPwd);
//
//
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @GET(Constant.SERVER_URL + "api/route/details/route123")
//    Observable<BaseResp<String>> test();
//
//    /**
//     * 上传任务
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @POST(Constant.SERVER_URL + "api/tasks/")
//    Observable<BeanRespCommon> taskUpLoad(@Body BeanReqUploadTask req);
//
//
//    /**
//     * 告警上报接口
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @POST(Constant.SERVER_URL + "api/alarmInformation")
//    Observable<BeanRespCommon> reqWarning(@Body BeanReqAlarmInformation bean);
//
//    /**
//     * 巡检感应点信息上报
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @PATCH(Constant.SERVER_URL + "api/taskRf/{taskCode}/{urmpRfCode}/{state}")
//    Observable<BeanRespCommon> reqTaskRf(@Path("taskCode") String taskCode, @Path("urmpRfCode") String urmpRfCode, @Path("state") String state);
//
//    /**
//     * 状态：未执行/执行中/已完成)
//     * 手机端登陆接口
//     * ?userPassWord=123
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @GET(Constant.SERVER_URL + "api/login/mobile/ceshi1?userPassword=pwd123")
//    Observable<BaseResp<String>> mobileLogin();
//
//    /**
//     * 手机端登出接口
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @GET(Constant.SERVER_URL + "api/logout/mobile")
//    Observable<BeanRespCommon> mobileLogout();
//
//    /**
//     * 图片上传接口
//     */
//    @Multipart
//    @Headers({HEADER_IMG_ACCEPT, HEADER_CONNECTION})
//    @POST(Constant.UPLOADPIC_URL + "upload")
//    Observable<BeanRespUploadPic> uploadPic(@Part MultipartBody.Part file);
//
//    /**
//     * 图片下载
//     */
//    @GET
//    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);
//
//    /**
//     * 基础数据下载
//     */
//    @Headers({HEADER_ACCEPT, HEADER_CONTENTTYPE, HEADER_CONNECTION})
//    @GET(Constant.SERVER_URL + "urmp/rest/inspectionService/queryAllRegion/")
//    Observable<List<BaseDataDto>> getBaseData();
}
