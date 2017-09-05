package com.tiantiannews.mvp.contract;

import com.network.AppExecutors;
import com.tiantiannews.mvp.BasePresenter;
import com.tiantiannews.mvp.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showProgress();

        void hideProgress();

        void setLoginError(String error);

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {

        void loadTasks(boolean forceUpdate);

        void login(String userName, String password, AppExecutors executors);

        void addObserve();
    }
}
