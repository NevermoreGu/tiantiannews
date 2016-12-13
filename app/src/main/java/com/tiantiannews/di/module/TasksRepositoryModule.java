package com.tiantiannews.di.module;


import android.content.Context;

import com.tiantiannews.data.source.Local;
import com.tiantiannews.data.source.Remote;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.data.source.local.TasksLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class TasksRepositoryModule {

    @Singleton
    @Provides
    @Local
    TasksDataSource provideTasksLocalDataSource(Context context) {
        return new TasksLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    TasksDataSource provideTasksRemoteDataSource() {
//        return new FakeTasksRemoteDataSource();
    }

}
