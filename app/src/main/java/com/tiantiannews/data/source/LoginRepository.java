package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.network.AppExecutors;
import com.network.NetworkBoundResource;
import com.rxhandler.core.RxErrorHandler;
import com.tiantiannews.data.bean.result.UserResult;

import javax.inject.Inject;

import rx.Observable;


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
    public Observable<ApiResponse> getTasks(final String content) {
        NetworkBoundResource<UserResult> networkBoundResource = new NetworkBoundResource<UserResult>(mAppExecutors, mRxErrorHandler) {
            @Override
            protected void saveCallResult(@NonNull UserResult item) {

            }

            @NonNull
            @Override
            protected Observable<ApiResponse<UserResult>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected Observable<ApiResponse<UserResult>> createCall() {
                return mTasksRemoteDataSource.getTasks(content);
            }
        };
        return mTasksRemoteDataSource.getTasks(content);
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
