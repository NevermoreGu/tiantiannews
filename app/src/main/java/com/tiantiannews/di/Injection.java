
package com.tiantiannews.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tiantiannews.data.source.TasksRepository;
import com.tiantiannews.data.source.local.TasksLocalDataSource;
import com.tiantiannews.data.source.remote.TasksRemoteDataSource;
import com.tiantiannews.utils.schedulers.BaseSchedulerProvider;
import com.tiantiannews.utils.schedulers.SchedulerProvider;


public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        return new TasksRepository(new TasksRemoteDataSource(),
                new TasksLocalDataSource(context, provideSchedulerProvider()));
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
