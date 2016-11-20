package com.tiantiannews.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;
import com.tiantiannews.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observable;


public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    public TasksLocalDataSource(@NonNull Context context,
                                 @NonNull BaseSchedulerProvider schedulerProvider) {

//        TasksDbHelper dbHelper = new TasksDbHelper(context);
//        SqlBrite sqlBrite = SqlBrite.create();
//        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, schedulerProvider.io());
//        mTaskMapperFunction = new Func1<Cursor, Task>() {
//            @Override
//            public Task call(Cursor c) {
//                String itemId = c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_ENTRY_ID));
//                String title = c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_TITLE));
//                String description =
//                        c.getString(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_DESCRIPTION));
//                boolean completed =
//                        c.getInt(c.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_COMPLETED)) == 1;
//                return new Task(title, description, itemId, completed);
//            }
//        };
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
