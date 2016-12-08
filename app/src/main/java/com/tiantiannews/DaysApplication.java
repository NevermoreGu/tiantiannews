package com.tiantiannews;

import com.tiantiannews.base.BaseApplication;
import com.tiantiannews.di.component.AppComponent;
import com.tiantiannews.di.component.DaggerAppComponent;
import com.tiantiannews.di.module.AppModule;
import com.tiantiannews.di.module.RemoteDataSourceModule;

public class DaysApplication extends BaseApplication {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .remoteDataSourceModule(new RemoteDataSourceModule())
                .clientModule(getClientModule())
                .build();
    }

    @Override
    protected String getBaseUrl() {
        return "http://58.215.50.61:19080/";
    }

}
