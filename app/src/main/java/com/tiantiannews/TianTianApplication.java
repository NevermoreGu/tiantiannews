package com.tiantiannews;

import com.network.di.module.ClientModule;
import com.tiantiannews.base.BaseApplication;
import com.tiantiannews.di.component.AppComponent;
import com.tiantiannews.di.component.DaggerAppComponent;
import com.tiantiannews.di.module.AppModule;

public class TianTianApplication extends BaseApplication {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    @Override
    protected String getBaseUrl() {
        return null;
    }

}
