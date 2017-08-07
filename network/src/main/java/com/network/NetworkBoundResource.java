package com.network;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.rxhandler.core.RxErrorHandler;
import com.rxhandler.handler.ErrorHandleSubscriber;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 * @param <ResultType>
 */
public abstract class NetworkBoundResource<ResultType> {

    private final AppExecutors mAppExecutors;
    private final RxErrorHandler mRxErrorHandler;

    //被观察对象
    private Observable<ApiResponse<ResultType>> result;

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors,RxErrorHandler rxErrorHandler) {
        this.mAppExecutors = appExecutors;
        this.mRxErrorHandler = rxErrorHandler;
        final Observable<ApiResponse<ResultType>> dbSource = loadFromDb();
        if (shouldFetch(dbSource)) {
            fetchFromNetwork(dbSource);
        } else {
            result = dbSource;
        }
    }

    private void fetchFromNetwork(final Observable<ApiResponse<ResultType>> dbSource) {
        Observable<ApiResponse<ResultType>> apiResponse = createCall();
        apiResponse
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<ApiResponse<ResultType>>(mRxErrorHandler) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(final ApiResponse<ResultType> response) {
                        if (response.isSuccessful()) {
                            mAppExecutors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    saveCallResult(processResponse(response));
                                    mAppExecutors.mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
////                                    result.addSource(loadFromDb(),
////                                        newData -> result.setValue(Resource.success(newData)))
                                        }
                                    });
                                }
                            });
                        } else {
                            onFetchFailed();
////                    result.addSource(dbSource,
////                            newData -> result.setValue(Resource.error(response.errorMessage, newData)));
                        }
                    }
                });
    }

    protected void onFetchFailed() {
    }

    /**
     * 处理api获取的数据
     *
     * @param response
     * @return
     */
    @WorkerThread
    protected ResultType processResponse(ApiResponse<ResultType> response) {
        return response.body;
    }

    /**
     * 保存api获取的数据到数据库
     *
     * @param item
     */
    @WorkerThread
    protected abstract void saveCallResult(@NonNull ResultType item);


    @MainThread
    protected boolean shouldFetch(@Nullable Observable<ApiResponse<ResultType>> data) {
        return true;
    }

    /**
     * 从数据库获取数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<ResultType>> loadFromDb();

    /**
     * 调取api获取数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<ResultType>> createCall();
}
