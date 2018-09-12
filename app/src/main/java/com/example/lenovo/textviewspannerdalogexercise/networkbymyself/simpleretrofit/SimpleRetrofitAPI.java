package com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit;

import com.example.lenovo.textviewspannerdalogexercise.networkbymyself.simpleretrofit.bean.BeanSimpleRetrofitResp;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xue on 2018/8/27.
 */

public interface SimpleRetrofitAPI {

    String BASEURL = "http://gank.io/api/";

    @GET("data/{category}/{pagecount}/{page}")
    Observable<BeanSimpleRetrofitResp> requestData(
            @Path("category") String category,
            @Path("pagecount") int countPerPage,
            @Path("page") int page
    );
}
