package com.network.di.module;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import com.network.BuildConfig;
import com.network.http.HttpHandler;
import com.network.http.RequestIntercept;
import com.rxhandler.core.RxErrorHandler;
import com.rxhandler.handler.listener.ResponseErrorListener;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ClientModule {

    private static final int TOME_OUT = 10;
    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//缓存文件最大值为10Mb
    private HttpUrl mApiUrl;
    private HttpHandler mHandler;
    private Interceptor[] mInterceptors;
    private ResponseErrorListener mErrorListener;

    private ClientModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
        this.mErrorListener = builder.responseErrorListener;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param
     * @param intercept 拦截器
     * @description:提供OkhttpClient
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache, Interceptor intercept) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return configureClient(okHttpClient, cache, intercept);
    }

    /**
     * @param client
     * @param httpUrl
     * @description: 提供retrofit
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, HttpUrl httpUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, httpUrl);
    }

    /**
     * 提供缓存地址
     */

    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return new File(Environment.getExternalStorageDirectory() + "/day_day_news/caches");
    }

    @Singleton
    @Provides
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//设置缓存路径和大小
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mApiUrl;
    }

//    @Singleton
//    @Provides
//    Cache provideCache(File cacheFile) {
//        return new Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//设置缓存路径和大小
//    }


    @Singleton
    @Provides
    Interceptor provideIntercept() {
        return new RequestIntercept(mHandler);//打印请求信息的拦截器
    }

    /**
     * 提供RXCache客户端
     *
     * @param cacheDir 缓存路径
     * @return
     */
//    @Singleton
//    @Provides
//    RxCache provideRxCache(File cacheDir) {
//        return new RxCache
//                .Builder()
//                .persistence(cacheDir, new GsonSpeaker());
//    }


    /**
     * 提供处理Rxjava错误的管理器
     *
     * @return
     */
    @Singleton
    @Provides
    RxErrorHandler proRxErrorHandler(Application application) {
        return RxErrorHandler
                .builder()
                .with(application)
                .responseErrorListener(mErrorListener)
                .build();
    }

    /**
     * 提供权限管理类,用于请求权限,适配6.0的权限管理
     * @param application
     * @return
     */
//    @Singleton
//    @Provides
//    RxPermissions provideRxPermissions(Application application) {
//        return RxPermissions.getInstance(application);
//    }


    /**
     * @param builder
     * @param client
     * @param httpUrl
     * @return
     * @author: jess
     * @date 8/30/16 1:15 PM
     * @description:配置retrofit
     */
    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl) {
        return builder
                .baseUrl(httpUrl)//域名
                .client(client)//设置okhttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .build();
    }

    /**
     * 配置okhttpclient
     *
     * @param okHttpClient
     * @return
     */
    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient, Cache cache, Interceptor intercept) {

        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        if (BuildConfig.LOG_DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(TOME_OUT, TimeUnit.SECONDS)
                .readTimeout(TOME_OUT, TimeUnit.SECONDS)
                .cache(cache)//设置缓存
                .addNetworkInterceptor(intercept);
        if (mInterceptors != null && mInterceptors.length > 0) {//如果外部提供了interceptor的数组则遍历添加
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder
                .build();
    }

    public static final class Builder {
        private HttpUrl apiUrl = HttpUrl.parse("https://api.github.com/");
        private HttpHandler handler;
        private Interceptor[] interceptors;
        private ResponseErrorListener responseErrorListener;

        private Builder() {
        }

        public Builder baseurl(String baseurl) {//基础url
            if (TextUtils.isEmpty(baseurl)) {
                throw new IllegalArgumentException("base_url can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public Builder httpHandler(HttpHandler handler) {//用来处理http响应结果
            this.handler = handler;
            return this;
        }

        public Builder interceptors(Interceptor[] interceptors) {//动态添加任意个interceptor
            this.interceptors = interceptors;
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener listener) {//处理所有Rxjava的onError逻辑
            this.responseErrorListener = listener;
            return this;
        }


        public ClientModule build() {
            if (apiUrl == null) {
                throw new IllegalStateException("baseurl is required");
            }
            return new ClientModule(this);
        }


    }

//    .addNetworkInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//            Request request = chain.request();
//            if(!DeviceUtils.netIsConnected(UiUtils.getContext())){
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//                LogUtils.warnInfo("http","no network");
//            }
//            Response originalResponse = chain.proceed(request);
//            if(DeviceUtils.netIsConnected(UiUtils.getContext())){
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            }else{
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    })

}
