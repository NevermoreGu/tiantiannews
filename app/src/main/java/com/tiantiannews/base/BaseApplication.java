package com.tiantiannews.base;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.network.di.module.ClientModule;
import com.network.http.HttpHandler;
import com.rxhandler.handler.listener.ResponseErrorListener;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tiantiannews.BuildConfig;

import okhttp3.Interceptor;

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    private RefWatcher mRefWatcher;

    public static boolean CheckWifi;

    private ClientModule mClientModule;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.mClientModule = ClientModule//用于提供okhttp和retrofit的单列
                .builder()
                .baseurl(getBaseUrl())
                .httpHandler(getHttpHandler())
                .interceptors(getInterceptors())
                .responseErrorListener(getResponseErrorListener())
                .build();

        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {
                MemorySizeCalculator calculator = new MemorySizeCalculator(instance);
                int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
                int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
                Glide.get(instance).clearMemory();
                System.gc();
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {

            }
        });
        installLeakCanary();
    }


    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * leakCanary内存泄露检查
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    protected abstract String getBaseUrl();

    public ClientModule getClientModule() {
        return mClientModule;
    }

    /**
     * 这里可以提供一个全局处理http响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * 默认不实现,如果有需求可以重写此方法
     *
     * @return
     */
    protected HttpHandler getHttpHandler() {
        return null;
    }

    /**
     * 用来提供interceptor,如果要提供额外的interceptor可以让子application实现此方法
     *
     * @return
     */
    protected Interceptor[] getInterceptors() {
        return null;
    }


    /**
     * 用来提供处理所有错误的监听
     * 如果要使用ErrorHandleSubscriber(默认实现Subscriber的onError方法)
     * 则让子application重写此方法
     * @return
     */
    protected ResponseErrorListener getResponseErrorListener() {
        return new ResponseErrorListener() {
            @Override
            public void handleResponseError(Context context, Exception e) {

            }
        };
    }
}