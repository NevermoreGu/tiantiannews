package com.tiantiannews.mvp;

import android.support.annotation.NonNull;

import com.tiantiannews.base.BaseModel;

import java.util.List;

public interface TasksContract {

    interface View<T> extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<BaseModel<T>> tasks);

        void showAddTask();

        void showTaskDetailsUi(String taskId);

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        void showCompletedTasksCleared();

        void showLoadingTasksError();

        void showNoTasks();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    interface Presenter<T> extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();

        void openTaskDetails(@NonNull BaseModel<T> requestedTask);

        void completeTask(@NonNull BaseModel<T> completedTask);

        void activateTask(@NonNull BaseModel<T> activeTask);

        void clearCompletedTasks();

//        void setFiltering(TasksFilterType requestType);
//
//        TasksFilterType getFiltering();
    }
}
