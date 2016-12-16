package com.tiantiannews.data.source.remote;

import android.support.annotation.NonNull;

import com.tiantiannews.api.ApiParams;
import com.tiantiannews.api.ApiService;
import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.di.module.RemoteDataSourceManager;

import rx.Observable;


public class TasksRemoteDataSource implements TasksDataSource {

    private RemoteDataSourceManager mRemoteDataSourceManager;

    public TasksRemoteDataSource(RemoteDataSourceManager remoteDataSourceManager) {
        this.mRemoteDataSourceManager = remoteDataSourceManager;
    }

    @Override
    public Observable<BaseModel> getTasks() {
        ApiService apiService = mRemoteDataSourceManager.getApiService();
        UserRequest userRequest = ApiParams.getLoginParams("13951894334", "000000");
        Observable observable = apiService.login(userRequest);
        return observable;
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
