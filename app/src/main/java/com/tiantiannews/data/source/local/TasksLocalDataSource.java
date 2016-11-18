package com.tiantiannews.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;

import java.util.List;

import rx.Observable;


public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    public TasksLocalDataSource(@NonNull Context context) {

    }

    @Override
    public Observable<List<BaseModel>> getTasks() {
        return null;
    }

    @Override
    public Observable<BaseModel> getTask(@NonNull String taskId) {
        return null;
    }

    @Override
    public void saveTask(@NonNull BaseModel task) {

    }

    @Override
    public void completeTask(@NonNull BaseModel task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull BaseModel task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
