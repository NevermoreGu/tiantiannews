package com.tiantiannews.mvp.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.network.AppExecutors;
import com.network.Resource;
import com.network.Status;
import com.tiantiannews.R;
import com.tiantiannews.api.ApiParams;
import com.tiantiannews.base.BaseApplication;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.bean.result.UserResult;
import com.tiantiannews.data.repository.LoginRepository;
import com.tiantiannews.di.scope.ActivityScope;
import com.tiantiannews.mvp.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginRepository mLoginRepository;

    private final LoginContract.View mLoginView;

    private CompositeDisposable mSubscriptions;

    private LiveData<Resource<UserResult>> user;

    @Inject
    public LoginPresenter(LoginRepository loginRepository, LoginContract.View loginView) {
        mLoginRepository = loginRepository;
        mLoginView = loginView;
        mSubscriptions = new CompositeDisposable();
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
        user = mLoginRepository.getTasks(content);
        user.observe((LifecycleOwner) mLoginView, new Observer<Resource<UserResult>>() {
            @Override
            public void onChanged(@Nullable Resource<UserResult> userResultResource) {
                if (userResultResource.status.equals(Status.SUCCESS)) {
                    mLoginView.setLoginError(BaseApplication.getInstance().getResources().getString(R.string.login_success));
                } else if (userResultResource.status.equals(Status.ERROR)) {
                    mLoginView.setLoginError(userResultResource.message);
                } else {

                }
            }
        });
    }

    @Override
    public void addObserve() {

    }

}
