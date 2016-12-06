package com.tiantiannews.data.source.remote;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;

import rx.Observable;

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    public TasksRemoteDataSource() {}

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<BaseModel> getTasks(@NonNull String key) {
        return null;
    }

    @Override
    public void saveTasks(@NonNull BaseModel task) {

    }
    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String key) {

    }
}
