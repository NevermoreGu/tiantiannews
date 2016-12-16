package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;

import rx.Observable;

public interface TasksDataSource<T> {

    Observable<BaseModel<T>> getTasks();

    void saveTasks(@NonNull BaseModel<T> task);

    void deleteAllTasks();

    void deleteTask(@NonNull String key);
}
