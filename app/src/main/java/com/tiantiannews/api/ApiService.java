package com.tiantiannews.api;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.bean.result.UserResult;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("saasapi/saasapi")
    Observable<BaseModel<UserResult>> login(@Body UserRequest request);
}
