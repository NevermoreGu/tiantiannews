package com.tiantiannews.ui.activity;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.ui.widget.AppBar;

import butterknife.BindView;

public class ForgetPasswordActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initVariables() {

    }

    @Override
    protected void initAppBar() {
        super.initAppBar();
        appBar.setAppBarTitle(R.string.get_pass);
    }

    @Override
    public void initViews() {
        initAppBar();
    }

    @Override
    public void loadData() {

    }
}
