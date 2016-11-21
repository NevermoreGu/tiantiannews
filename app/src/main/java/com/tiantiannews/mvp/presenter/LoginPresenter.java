package com.tiantiannews.mvp.presenter;

import android.support.annotation.NonNull;

import com.tiantiannews.data.source.TasksRepository;
import com.tiantiannews.mvp.contract.LoginContract;
import com.tiantiannews.utils.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class LoginPresenter implements LoginContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final LoginContract.View mTasksView;

    private final BaseSchedulerProvider mSchedulerProvider;

    private CompositeSubscription mSubscriptions;

    public LoginPresenter(@NonNull TasksRepository tasksRepository, @NonNull LoginContract.View loginView,
                          @NonNull BaseSchedulerProvider schedulerProvider) {
        mTasksRepository = tasksRepository;
        mTasksView = loginView;
        mSchedulerProvider = schedulerProvider;
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
