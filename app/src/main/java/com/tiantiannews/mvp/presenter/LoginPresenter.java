package com.tiantiannews.mvp.presenter;

import android.support.annotation.NonNull;

import com.tiantiannews.data.source.TasksRepository;
import com.tiantiannews.mvp.contract.LoginContract;
import com.tiantiannews.utils.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

public class LoginPresenter implements LoginContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final LoginContract.View mTasksView;

    private CompositeSubscription mSubscriptions;

    public LoginPresenter(@NonNull TasksRepository tasksRepository, @NonNull LoginContract.View loginView,
                          @NonNull BaseSchedulerProvider schedulerProvider) {
        mTasksRepository = tasksRepository;
        mTasksView = loginView;
        mSubscriptions = new CompositeSubscription();
        mTasksView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {

    }
}
