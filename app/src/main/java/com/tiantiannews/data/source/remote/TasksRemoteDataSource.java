package com.tiantiannews.data.source.remote;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;

import java.util.List;

import rx.Observable;

public class TasksRemoteDataSource implements TasksDataSource {

//    private static TasksRemoteDataSource INSTANCE;

    public TasksRemoteDataSource() {}

//    public static TasksRemoteDataSource getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new TasksRemoteDataSource();
//        }
//        return INSTANCE;
//    }

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
