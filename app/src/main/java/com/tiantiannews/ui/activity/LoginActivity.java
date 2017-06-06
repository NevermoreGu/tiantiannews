package com.tiantiannews.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.di.component.DaggerLoginComponent;
import com.tiantiannews.di.module.LoginPresenterModule;
import com.tiantiannews.di.module.LoginRepositoryModule;
import com.tiantiannews.mvp.presenter.LoginPresenter;
import com.tiantiannews.ui.fragment.LoginFragment;
import com.utils.ActivityUtils;
import com.utils.TDevice;

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
        Log.d("loginActivity", "test root is " + ActivityUtils.isRootAvailable());
        int s = TDevice.dip2pix(mContext, 10);
        int ss = TDevice.dip2px(mContext, 10);
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginRepositoryModule(new LoginRepositoryModule())
                .loginPresenterModule(new LoginPresenterModule(loginFragment))
                .build().inject(this);

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
