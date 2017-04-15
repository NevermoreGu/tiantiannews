package com.tiantiannews.data.source.remote;

import android.support.annotation.NonNull;

import com.tiantiannews.api.ApiService;
import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.di.module.RemoteDataSourceManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;


public class LoginRemoteDataSource implements TasksDataSource {

    private RemoteDataSourceManager mRemoteDataSourceManager;

    public LoginRemoteDataSource(RemoteDataSourceManager remoteDataSourceManager) {
        this.mRemoteDataSourceManager = remoteDataSourceManager;
    }

    @Override
    public Observable<BaseModel> getTasks(String content) {
        ApiService apiService = mRemoteDataSourceManager.getApiService();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),content);
        Observable observable = apiService.login(requestBody);
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
