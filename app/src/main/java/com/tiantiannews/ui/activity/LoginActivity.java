package com.tiantiannews.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.ui.fragment.LoginFragment;

public class LoginActivity extends BaseActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_content_login, loginFragment);
            fragmentTransaction.commit();
        }
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
