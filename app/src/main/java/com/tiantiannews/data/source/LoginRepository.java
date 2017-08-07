package com.tiantiannews.data.source;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.network.AppExecutors;
import com.network.NetworkBoundResource;
import com.network.Resource;
import com.rxhandler.core.RxErrorHandler;
import com.tiantiannews.data.bean.result.UserResult;

import javax.inject.Inject;


public class LoginRepository implements TasksDataSource {

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


    @Override
    public LiveData<Resource<UserResult>> getTasks(final String content) {
        NetworkBoundResource<UserResult> networkBoundResource = new NetworkBoundResource<UserResult>(mAppExecutors, mRxErrorHandler) {
            @Override
            protected void saveCallResult(@NonNull UserResult item) {

            }

            @NonNull
            @Override
            protected LiveData<UserResult> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResult>> createCall() {
                return mTasksRemoteDataSource.getTasks(content);
            }
        };
        return networkBoundResource.asLiveData();
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
