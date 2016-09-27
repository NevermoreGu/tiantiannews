package com.tiantiannews.utils.net;

import com.tiantiannews.api.ApiService;
import com.tiantiannews.base.BaseModel;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNet<T> {

    private NetBuilder netBuilder;
    private Retrofit retrofit;
    private OkHttpClient httpClient;

//    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//        @Override public void log(String message) {
//            HLog.d("OKhttp",message);
//        }
//    });

    public RetrofitNet(NetBuilder netBuilder) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(netBuilder.url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        this.netBuilder = netBuilder;
    }

    /**
     * 容易引起内存泄漏
     */
//    public RetrofitNet getInstance(NetBuilder netBuilder) {
//        if (mInstance == null) {
//            mInstance = new RetrofitNet(netBuilder);
//        }
//        return mInstance;
//    }
    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }

    public void addToRequestQueue(Call<BaseModel<T>> call) {

        if (netBuilder == null) {
            return;
        }
        final NetCallBack netCallBack = netBuilder.callBack;

        netCallBack.onStart();

        call.enqueue(new Callback<BaseModel<T>>() {
            @Override
            public void onResponse(Call<BaseModel<T>> call, Response<BaseModel<T>> response) {
                if (response.raw().code() == 200) {
                    netCallBack.onFinish();
                    if (response.body().result == 0) {

                        netCallBack.onResponse(response.body());
                    } else {
                        netCallBack.onErrorResponse(response.body().resultNote);
                    }

                } else {
                    onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
                }

            }

            @Override
            public void onFailure(Call<BaseModel<T>> call, Throwable t) {
                netCallBack.onFinish();
                String error = "";
                if (t instanceof ConnectException) {// 不能在指定的ip和端口上建立连接
                    error = "";
                } else if (t instanceof SocketTimeoutException) {// 读取数据超时
                    error = "";
                } else if (t instanceof UnknownHostException) {
                    error = "";
                } else if (t instanceof InterruptedIOException) {
                    error = "";
                } else if (t instanceof RuntimeException) {
                    error = "";
                } else if (t instanceof IOException) {
                    error = "未知错误";
                }
                netCallBack.onErrorResponse(error);

            }

        });
    }

}
