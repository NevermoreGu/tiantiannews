package com.tiantiannews.data.source.local;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.source.TasksDataSource;

import rx.Observable;

public class TasksLocalDataSource implements TasksDataSource {

    public TasksLocalDataSource() {

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
    public Observable<BaseModel> getTasks(String content) {
        return null;
    }

    @Override
    public void saveTasks(@NonNull BaseModel task) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String key) {

    }
}
