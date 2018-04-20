package com.tiantiannews.api;

import com.network.ApiResponse;
import com.tiantiannews.data.bean.result.UserResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("saasapi/saasapi")
    Observable<ApiResponse<UserResult>> login(@Body RequestBody request);
}
