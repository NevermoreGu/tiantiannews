package com.tiantiannews.di.module;

import com.tiantiannews.mvp.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginPresenterModule {

    private final LoginContract.View mView;

    public LoginPresenterModule(LoginContract.View view) {
        mView = view;
    }

    @Provides
    LoginContract.View provideTasksContractView() {
        return mView;
    }

}
