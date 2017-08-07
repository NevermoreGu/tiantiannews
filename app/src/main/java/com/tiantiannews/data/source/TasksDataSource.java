package com.tiantiannews.data.source;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.network.Resource;

public interface TasksDataSource<T> {

    LiveData<Resource<T>> getTasks(String content);

    void saveTasks(@NonNull ApiResponse<T> task);

    void deleteAllTasks();

    void deleteTask(@NonNull String key);
}
