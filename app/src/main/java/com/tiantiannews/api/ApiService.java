package com.tiantiannews.api;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.UserRequest;
import com.tiantiannews.data.bean.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("saasapi/saasapi")
    Call<BaseModel<UserResult>> login(@Body UserRequest request);
}
