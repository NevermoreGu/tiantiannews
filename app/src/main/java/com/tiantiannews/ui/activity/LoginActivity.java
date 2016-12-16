package com.tiantiannews.ui.activity;

import android.os.Bundle;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.di.component.DaggerLoginComponent;
import com.tiantiannews.di.component.DaggerLoginRepositoryComponent;
import com.tiantiannews.di.component.LoginRepositoryComponent;
import com.tiantiannews.di.module.LoginPresenterModule;
import com.tiantiannews.di.module.LoginRepositoryModule;
import com.tiantiannews.mvp.presenter.LoginPresenter;
import com.tiantiannews.ui.fragment.LoginFragment;
import com.tiantiannews.utils.ActivityUtils;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    @Inject
    LoginPresenter mLoginPresenter;
    private LoginFragment loginFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            loginFragment = new LoginFragment();
            ActivityUtils.addFragmentToActivity(mFragmentManager, loginFragment, R.id.fl_content_login);
        }
        LoginRepositoryComponent daggerLoginRepositoryComponent = DaggerLoginRepositoryComponent.builder()
                .appComponent(getAppComponent())
                .loginRepositoryModule(new LoginRepositoryModule())
                .build();

        DaggerLoginComponent.builder()
                .loginRepositoryComponent(daggerLoginRepositoryComponent)
                .loginPresenterModule(new LoginPresenterModule(loginFragment)).build()
                .inject(this);

    }
//
//    @Override
//    protected void setupActivityComponent(AppComponent appComponent) {


//    }

    @Override
    public void initVariables() {
        super.initVariables();
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
    }

}
