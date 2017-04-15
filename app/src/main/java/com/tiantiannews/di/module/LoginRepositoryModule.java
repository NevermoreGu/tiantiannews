package com.tiantiannews.di.module;


import com.tiantiannews.data.source.Local;
import com.tiantiannews.data.source.Remote;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.data.source.local.LoginLocalDataSource;
import com.tiantiannews.data.source.remote.LoginRemoteDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginRepositoryModule {

    @Provides
    @Local
    TasksDataSource provideTasksLocalDataSource() {
        return new LoginLocalDataSource();
    }

    @Provides
    @Remote
    TasksDataSource provideTasksRemoteDataSource(RemoteDataSourceManager RemoteDataSourceManager) {
        return new LoginRemoteDataSource(RemoteDataSourceManager);
    }

}
