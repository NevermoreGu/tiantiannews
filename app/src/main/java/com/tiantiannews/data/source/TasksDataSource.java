package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.network.ApiResponse;

import rx.Observable;

public interface TasksDataSource<T> {

    Observable<ApiResponse<T>> getTasks(String content);

    void saveTasks(@NonNull ApiResponse<T> task);

    void deleteAllTasks();

    void deleteTask(@NonNull String key);
}
