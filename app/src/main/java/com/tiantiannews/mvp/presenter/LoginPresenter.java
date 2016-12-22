package com.tiantiannews.mvp.presenter;

import com.google.gson.Gson;
import com.tiantiannews.api.ApiParams;
import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.source.LoginRepository;
import com.tiantiannews.di.scope.ActivityScope;
import com.tiantiannews.mvp.contract.LoginContract;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginRepository mTasksRepository;

    private final LoginContract.View mTasksView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public LoginPresenter(LoginRepository tasksRepository, LoginContract.View loginView) {
        mTasksRepository = tasksRepository;
        mTasksView = loginView;
        mSubscriptions = new CompositeSubscription();
        mTasksView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadTasks(false);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        loadTasks(forceUpdate, true);
    }

    private void loadTasks(final boolean forceUpdate, final boolean showLoadingUI) {
        mSubscriptions.clear();
        UserRequest userRequest = ApiParams.getLoginParams("13951894334", "000000");
        Gson gson = new Gson();
        String content = gson.toJson(userRequest);
        Observable<BaseModel> observable = mTasksRepository.getTasks(content);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                        String error = "";
                        if (t instanceof ConnectException) {// 不能在指定的ip和端口上建立连接
                            error = "";
                        } else if (t instanceof SocketTimeoutException) {// 读取数据超时
                            error = "";
                        } else if (t instanceof UnknownHostException) {
                            error = "";
                        } else if (t instanceof InterruptedIOException) {
                            error = "";
                        } else if (t instanceof RuntimeException) {
                            error = "";
                        } else if (t instanceof IOException) {
                            error = "未知错误";
                        }

                    }

                    @Override
                    public void onNext(BaseModel response) {
                        String S ="";

                    }
                });
    }

}
