package com.tiantiannews.di.component;

import android.app.Application;

import com.network.AppExecutors;
import com.network.di.module.ClientModule;
import com.network.di.module.ExecutorsModule;
import com.rxhandler.core.RxErrorHandler;
import com.tiantiannews.di.module.AppModule;
import com.tiantiannews.di.module.RemoteDataSourceManager;
import com.tiantiannews.di.module.RemoteDataSourceModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, RemoteDataSourceModule.class, ExecutorsModule.class})
public interface AppComponent {

    Application getApplication();

    RemoteDataSourceManager getRemoteDataSourceManager();

    OkHttpClient okHttpClient();

    RxErrorHandler rxErrorHandler();

    AppExecutors getExecutorsModule();
}
