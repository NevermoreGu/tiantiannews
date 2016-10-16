package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;

import java.util.List;

import rx.Observable;

public interface TasksDataSource<T> {

    Observable<List<BaseModel<T>>> getTasks();

    Observable<BaseModel<T>> getTask(@NonNull String taskId);

    void saveTask(@NonNull BaseModel<T> task);

    void completeTask(@NonNull BaseModel<T> task);

    void completeTask(@NonNull String taskId);

    void activateTask(@NonNull BaseModel<T> task);

    void activateTask(@NonNull String taskId);

    void clearCompletedTasks();

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull String taskId);
}
