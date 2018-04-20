package com.tiantiannews.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.network.AppExecutors;
import com.network.NetworkBoundResource;
import com.network.Resource;
import com.rxhandler.core.RxErrorHandler;
import com.tiantiannews.data.bean.result.UserResult;
import com.tiantiannews.data.source.Local;
import com.tiantiannews.data.source.Remote;
import com.tiantiannews.data.source.TasksDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;


public class LoginRepository {

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    private AppExecutors mAppExecutors;
    private RxErrorHandler mRxErrorHandler;

    @Inject
    public LoginRepository(@Remote TasksDataSource tasksRemoteDataSource,
                           @Local TasksDataSource tasksLocalDataSource, AppExecutors appExecutors, RxErrorHandler rxErrorHandler) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
        mAppExecutors = appExecutors;
        mRxErrorHandler = rxErrorHandler;
    }

    public LiveData<Resource<UserResult>> getTasks(final String content) {
        NetworkBoundResource<UserResult> networkBoundResource = new NetworkBoundResource<UserResult>(mAppExecutors, mRxErrorHandler) {
            @Override
            protected void saveCallResult(@NonNull UserResult item) {

            }

            @NonNull
            @Override
            protected Observable<UserResult> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected Observable<ApiResponse<UserResult>> createCall() {
                return mTasksRemoteDataSource.getTasks(content);
            }
        };
        return networkBoundResource.asLiveData();
    }

    public void saveTasks(@NonNull ApiResponse task) {

    }

}
