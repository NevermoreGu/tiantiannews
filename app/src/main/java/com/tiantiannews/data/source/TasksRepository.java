package com.tiantiannews.data.source;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;

import java.util.List;

import rx.Observable;

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    public TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
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
