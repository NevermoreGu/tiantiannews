package com.tiantiannews.mvp.contract;

import com.tiantiannews.mvp.BasePresenter;
import com.tiantiannews.mvp.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showProgress();

        void hideProgress();

        void setLoginError();

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {

        void loadTasks(boolean forceUpdate);
    }
}
