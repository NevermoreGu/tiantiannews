package com.tiantiannews.mvp.presenter;

import com.google.gson.Gson;
import com.network.AppExecutors;
import com.tiantiannews.api.ApiParams;
import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.request.UserRequest;
import com.tiantiannews.data.source.LoginRepository;
import com.tiantiannews.di.scope.ActivityScope;
import com.tiantiannews.mvp.contract.LoginContract;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginRepository mLoginRepository;

    private final LoginContract.View mLoginView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public LoginPresenter(LoginRepository loginRepository, LoginContract.View loginView) {
        mLoginRepository = loginRepository;
        mLoginView = loginView;
        mSubscriptions = new CompositeSubscription();
        mLoginView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {

    }

    @Override
    public void login(String userName, String password, AppExecutors appExecutors) {
        mSubscriptions.clear();
        UserRequest userRequest = ApiParams.getLoginParams(userName, password);
        Gson gson = new Gson();
        final String content = gson.toJson(userRequest);
        Observable<BaseModel> observable = mLoginRepository.getTasks(content);

//        NetworkBoundResource<BaseModel> networkBoundResource = new NetworkBoundResource<BaseModel>(appExecutors) {
//            @Override
//            protected void saveCallResult(@NonNull BaseModel item) {
//
//            }
//
//            @NonNull
//            @Override
//            protected Observable<BaseModel> loadFromDb() {
//                return null;
//            }
//
//            @NonNull
//            @Override
//            protected Observable<ApiResponse<BaseModel>> createCall() {
//                return mLoginRepository.getTasks(content);
//            }
//        };

//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                        String error = "";
//                        if (t instanceof ConnectException) {// 不能在指定的ip和端口上建立连接
//                            error = "";
//                        } else if (t instanceof SocketTimeoutException) {// 读取数据超时
//                            error = "";
//                        } else if (t instanceof UnknownHostException) {
//                            error = "";
//                        } else if (t instanceof InterruptedIOException) {
//                            error = "";
//                        } else if (t instanceof RuntimeException) {
//                            error = "";
//                        } else if (t instanceof IOException) {
//                            error = "未知错误";
//                        }
//
//                    }
//
//                    @Override
//                    public void onNext(BaseModel response) {
//                        String S = "";
//
//                    }
//                });
    }

}
