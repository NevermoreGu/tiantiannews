package com.tiantiannews.ui.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.mvp.contract.LoginContract;
import com.tiantiannews.ui.activity.ForgetPasswordActivity;
import com.tiantiannews.ui.widget.DeleteEditText;
import com.tiantiannews.ui.widget.PassVisibleCheckBox;
import com.tiantiannews.ui.widget.progressbar.CircularLoadingProgressBar;
import com.tiantiannews.utils.ActivityUtils;
import com.tiantiannews.utils.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements TextWatcher, LoginContract.View {

    @BindView(R.id.et_login_name)
    DeleteEditText etLoginName;
    @BindView(R.id.et_login_pass)
    DeleteEditText etLoginPass;
    @BindView(R.id.img_pass_visible)
    PassVisibleCheckBox imgPassVisible;
    @BindView(R.id.tv_login_forget_pass)
    TextView tvLoginForgetPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.tv_login_qq)
    TextView tvLoginQQ;
    @BindView(R.id.tv_login_blog)
    TextView tvLoginBlog;
    @BindView(R.id.tv_login_chat)
    TextView tvLoginChat;
    @BindView(R.id.clp)
    CircularLoadingProgressBar circularLoadingProgressBar;

    private LoginContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        etLoginName.addTextChangedListener(this);
        etLoginPass.addTextChangedListener(this);

        imgPassVisible.setPassVisible(etLoginPass);

        ViewUtils.addTouchColor(btnLogin, R.color.colorPrimaryDark, R.color.colorPrimary);
        btnLogin.setEnabled(false);
        btnLogin.setTextScaleX(1.2f);

        ViewUtils.addTouchDrawable(tvLoginQQ, R.drawable.ic_login_way_qq_pressed, R.drawable.ic_login_way_qq_normal, 1);
        ViewUtils.addTouchDrawable(tvLoginBlog, R.drawable.ic_login_way_blog_pressed, R.drawable.ic_login_way_blog_normal, 1);
        ViewUtils.addTouchDrawable(tvLoginChat, R.drawable.ic_login_way_wx_pressed, R.drawable.ic_login_way_wx_normal, 1);
    }

    @Override
    public void loadData() {
        mPresenter.loadTasks(false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mPresenter.unSubscribe();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String userName = etLoginName.getText().toString().trim();
        String userPass = etLoginPass.getText().toString().trim();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)) {
            btnLogin.setBackgroundColor(getResources().getColor(
                    R.color.colorPrimary));
            btnLogin.setTextColor(getResources().getColor(R.color.white));
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setBackgroundColor(getResources().getColor(
                    R.color.white));
            btnLogin.setTextColor(getResources().getColor(R.color.gray));
            btnLogin.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.tv_login_forget_pass, R.id.btn_login, R.id.tv_login_register, R.id.tv_login_chat, R.id.tv_login_blog, R.id.tv_login_qq, R.id.clp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget_pass:
                ActivityUtils.openActivity(getActivity(), ForgetPasswordActivity.class);
                break;
            case R.id.btn_login:
                mPresenter.login(etLoginName.getText().toString().trim(),etLoginPass.getText().toString().trim());
                break;

            case R.id.tv_login_register:

                break;
            case R.id.img_pass_visible:
                break;
            case R.id.tv_login_chat:
                break;
            case R.id.tv_login_blog:
                break;
            case R.id.tv_login_qq:
                break;
            case R.id.clp:
//                circularLoadingProgressBar.startAnim();
                break;
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setLoginError() {

    }

    @Override
    public void navigateToHome() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
