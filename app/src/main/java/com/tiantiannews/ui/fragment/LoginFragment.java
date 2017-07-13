package com.tiantiannews.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.ui.widget.DeleteEditText;
import com.base.ui.widget.PassVisibleCheckBox;
import com.base.ui.widget.progressbar.CircularLoadingProgressBar;
import com.network.AppExecutors;
import com.tiantiannews.R;
import com.tiantiannews.aidl.IImageAidlInterface;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.data.bean.SelectPicturesInfo;
import com.tiantiannews.mvp.contract.LoginContract;
import com.tiantiannews.service.ImageService;
import com.tiantiannews.ui.activity.ForgetPasswordActivity;
import com.tiantiannews.ui.activity.SelectPicturesActivity;
import com.tiantiannews.ui.widget.progressbar.CircularRingPercentageView;
import com.tiantiannews.utils.TestCode;
import com.utils.ActivityUtils;
import com.utils.ViewUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.tiantiannews.data.bean.SelectPicturesInfo.EXTRA_PARAMETER;

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
    @BindView(R.id.pb_view)
    CircularRingPercentageView pbView;

    private LoginContract.Presenter mPresenter;
    private IImageAidlInterface iImageAidlInterface;
    private boolean canBind = false;
    private SelectPicturesInfo mSelectPicturesInfo;

    @Inject
    AppExecutors appExecutors;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initVariables() {
        mSelectPicturesInfo = new SelectPicturesInfo();
    }

    @Override
    public void initViews() {
        etLoginName.addTextChangedListener(this);
        etLoginPass.addTextChangedListener(this);

        imgPassVisible.setPassVisible(etLoginPass);

        ViewUtils.addTouchColor(btnLogin, R.color.colorPrimaryDark, R.color.colorPrimary, getActivity());
        btnLogin.setEnabled(false);
        btnLogin.setTextScaleX(1.2f);

        ViewUtils.addTouchDrawable(tvLoginQQ, R.drawable.ic_login_way_qq_pressed, R.drawable.ic_login_way_qq_normal, 1, getActivity());
        ViewUtils.addTouchDrawable(tvLoginBlog, R.drawable.ic_login_way_blog_pressed, R.drawable.ic_login_way_blog_normal, 1, getActivity());
        ViewUtils.addTouchDrawable(tvLoginChat, R.drawable.ic_login_way_wx_pressed, R.drawable.ic_login_way_wx_normal, 1, getActivity());
        pbView.setMaxColorNumber(20);
        pbView.setRoundBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pbView.setLineWidth(20f);
    }

    @Override
    public void loadData() {
        mPresenter.loadTasks(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (canBind) {
            Intent intentService = new Intent(getActivity(), ImageService.class);
            getActivity().bindService(intentService, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    iImageAidlInterface = IImageAidlInterface.Stub.asInterface(service);
                    try {
                        List<String> imageInfo = iImageAidlInterface.getImages();
                        mSelectPicturesInfo.setImage_list(imageInfo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            }, BIND_AUTO_CREATE);
            canBind = false;
        }
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
                mPresenter.login(etLoginName.getText().toString().trim(), etLoginPass.getText().toString().trim(), ((BaseActivity) getActivity()).getAppComponent().getExecutorsModule());
                break;

            case R.id.tv_login_register:

                break;
            case R.id.img_pass_visible:
                break;
            case R.id.tv_login_chat:
                canBind = true;
                Intent intentService = new Intent(getActivity(), ImageService.class);
                getActivity().startService(intentService);

                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_PARAMETER, mSelectPicturesInfo);
                ActivityUtils.openActivity(getActivity(), SelectPicturesActivity.class, bundle);
                break;
            case R.id.tv_login_blog:
                break;
            case R.id.tv_login_qq:
                TestCode.testGetApiFile(getActivity());
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
