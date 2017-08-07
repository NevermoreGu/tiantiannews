package com.tiantiannews.mvp.presenter;

import com.google.gson.Gson;
import com.network.AppExecutors;
import com.tiantiannews.api.ApiParams;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.source.LoginRepository;
import com.tiantiannews.di.scope.ActivityScope;
import com.tiantiannews.mvp.contract.LoginContract;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginRepository mLoginRepository;

    private final LoginContract.View mLoginView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public LoginPresenter(LoginRepository loginRepository, LoginContract.View loginView) {
        mLoginRepository = loginRepository;
        mLoginView = loginView;
        mSubscriptions = new CompositeSubscription();
        mLoginView.setPresenter(this);
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

    @Override
    public void login(String userName, String password, AppExecutors appExecutors) {
        mSubscriptions.clear();
        UserRequest userRequest = ApiParams.getLoginParams(userName, password);
        Gson gson = new Gson();
        final String content = gson.toJson(userRequest);
        mLoginRepository.getTasks(content);
    }

}
