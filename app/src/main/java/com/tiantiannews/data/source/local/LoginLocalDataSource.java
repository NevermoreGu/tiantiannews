package com.tiantiannews.data.source.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.network.ApiResponse;
import com.network.Resource;
import com.tiantiannews.data.bean.result.UserResult;
import com.tiantiannews.data.source.TasksDataSource;

public class LoginLocalDataSource implements TasksDataSource {

    public LoginLocalDataSource() {

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
    public LiveData<Resource<UserResult>> getTasks(String content) {
        return null;
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
