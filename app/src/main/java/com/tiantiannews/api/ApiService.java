package com.tiantiannews.api;

import android.arch.lifecycle.LiveData;

import com.network.ApiResponse;
import com.tiantiannews.data.bean.result.UserResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("saasapi/saasapi")
    LiveData<ApiResponse<UserResult>> login(@Body RequestBody request);
}
