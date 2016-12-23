package com.tiantiannews.data.source;

import com.tiantiannews.base.BaseModel;
import com.tiantiannews.utils.Rx.ErrorHandleSubscriber;
import com.tiantiannews.utils.net.NetCallBack;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataSourceUtils {

    private static DataSourceUtils mInstance;

    private DataSourceUtils() {

    }

    public static DataSourceUtils getInstance() {
        if (mInstance == null) {
            synchronized (DataSourceUtils.class) {
                if (mInstance == null) {
                    mInstance = new DataSourceUtils();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }


    public <T> void handlerData(Observable<BaseModel<T>> observable, final NetCallBack netCallBack) {
        netCallBack.onStart();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BaseModel<T>>() {
//
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
//                    public void onNext(BaseModel<T> response) {
//                        netCallBack.onResponse(response);
//                    }
//                });
        .subscribe(new ErrorHandleSubscriber<BaseModel<T>>() {
            @Override
            public void onNext(BaseModel<T> tBaseModel) {

            }
        });
    }
}
