package com.tiantiannews.ui.activity;

import android.content.Intent;
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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });
        // 指定 subscribe() 发生在 IO 线程
        observable.subscribeOn(Schedulers.io())
                // 指定 Subscriber 的回调发生在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }
}
