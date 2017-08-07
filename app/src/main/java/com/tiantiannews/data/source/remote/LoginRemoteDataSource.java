package com.tiantiannews.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.tiantiannews.api.ApiService;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.di.module.RemoteDataSourceManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginRemoteDataSource implements TasksDataSource {

    private RemoteDataSourceManager mRemoteDataSourceManager;

    public LoginRemoteDataSource(RemoteDataSourceManager remoteDataSourceManager) {
        this.mRemoteDataSourceManager = remoteDataSourceManager;
    }

    @Override
    public LiveData<ApiResponse> getTasks(String content) {
        ApiService apiService = mRemoteDataSourceManager.getApiService();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),content);
        LiveData observable = apiService.login(requestBody);
        return observable;
    }

    @Override
    public void saveTasks(@NonNull ApiResponse task) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String key) {

    }
}
