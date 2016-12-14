package com.tiantiannews.mvp.presenter;

import android.support.annotation.NonNull;

import com.tiantiannews.data.source.TasksRepository;
import com.tiantiannews.mvp.contract.LoginContract;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class LoginPresenter implements LoginContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final LoginContract.View mTasksView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public LoginPresenter(@NonNull TasksRepository tasksRepository, @NonNull LoginContract.View loginView) {
        mTasksRepository = tasksRepository;
        mTasksView = loginView;
        mSubscriptions = new CompositeSubscription();
        mTasksView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadTasks(false);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        loadTasks(forceUpdate, true);
    }

    private void loadTasks(final boolean forceUpdate, final boolean showLoadingUI) {
        mSubscriptions.clear();
    }
}
