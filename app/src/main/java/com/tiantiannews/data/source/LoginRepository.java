package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;

import javax.inject.Inject;

import rx.Observable;


public class LoginRepository implements TasksDataSource {
    
    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    @Inject
    public LoginRepository(@Remote TasksDataSource tasksRemoteDataSource,
                           @Local TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }


    @Override
    public Observable<BaseModel> getTasks(String content) {
        return mTasksRemoteDataSource.getTasks(content);
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
