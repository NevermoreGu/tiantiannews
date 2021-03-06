package com.tiantiannews;

import com.network.di.module.ExecutorsModule;
import com.network.http.HttpHandler;
import com.rxhandler.handler.listener.ResponseErrorListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.tiantiannews.base.BaseApplication;
import com.tiantiannews.di.component.AppComponent;
import com.tiantiannews.di.component.DaggerAppComponent;
import com.tiantiannews.di.module.AppModule;
import com.tiantiannews.di.module.RemoteDataSourceModule;
import com.utils.AppUncaughtExceptionHandler;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DaysApplication extends BaseApplication {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .remoteDataSourceModule(new RemoteDataSourceModule())
                .clientModule(getClientModule())
                .executorsModule(new ExecutorsModule())
                .build();
        CrashReport.initCrashReport(getApplicationContext(), "730d427925", true);
        AppUncaughtExceptionHandler crashHandler = AppUncaughtExceptionHandler.getInstance();
        crashHandler.init(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected String getBaseUrl() {
        return "https://test.cpsdna.com/";
    }

    @Override
    protected HttpHandler getHttpHandler() {
        return new HttpHandler() {
            @Override
            public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                return response;
            }

            @Override
            public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                return request;
            }
        };
    }

    @Override
    protected ResponseErrorListener getResponseErrorListener() {
        return super.getResponseErrorListener();
    }
}
