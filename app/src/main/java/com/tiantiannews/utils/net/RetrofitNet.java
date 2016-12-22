package com.tiantiannews.utils.net;

import com.tiantiannews.api.ApiService;
import com.tiantiannews.base.BaseModel;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitNet<T> {

    private NetBuilder netBuilder;
    private Retrofit retrofit;
    private static RetrofitNet mInstance;
    private static final int DEFAULT_TIMEOUT = 10;

//    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//        @Override public void log(String message) {
//            HLog.d("OKhttp",message);
//        }
//    });

    public RetrofitNet(NetBuilder netBuilder) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.
                connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
        OkHttpClient httpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(netBuilder.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
    public static RetrofitNet getInstance(NetBuilder netBuilder) {
        if (mInstance == null) {
            synchronized (RetrofitNet.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitNet(netBuilder);
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }

    public void addToRequestQueue(Observable<BaseModel<T>> observable) {

        if (netBuilder == null) {
            return;
        }
        final NetCallBack netCallBack = netBuilder.callBack;

        netCallBack.onStart();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {
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

                    @Override
                    public void onNext(BaseModel<T> response) {
                        netCallBack.onFinish();

                        netCallBack.onResponse(response);

                    }
                });
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
