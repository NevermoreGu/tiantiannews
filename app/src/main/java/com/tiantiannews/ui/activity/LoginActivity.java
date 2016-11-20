package com.tiantiannews.ui.activity;

import android.os.Bundle;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.di.Injection;
import com.tiantiannews.mvp.presenter.LoginPresenter;
import com.tiantiannews.ui.fragment.LoginFragment;
import com.tiantiannews.utils.ActivityUtils;

public class LoginActivity extends BaseActivity {

    private LoginPresenter mLoginPresenter;
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
        mLoginPresenter = new LoginPresenter(Injection.provideTasksRepository(getApplicationContext()),
                loginFragment,
                Injection.provideSchedulerProvider());
    }

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
