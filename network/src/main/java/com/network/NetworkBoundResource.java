package com.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.rxhandler.core.RxErrorHandler;
import com.rxhandler.handler.ErrorHandleSubscriber;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 * @param <ResultType>
 */
public abstract class NetworkBoundResource<ResultType> {

    private final AppExecutors mAppExecutors;
    private final RxErrorHandler mRxErrorHandler;

    //被观察对象
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors, RxErrorHandler rxErrorHandler) {
        this.mAppExecutors = appExecutors;
        this.mRxErrorHandler = rxErrorHandler;
//        result.setValue((Resource<ResultType>) Resource.loading(null));
        final Observable<ResultType> dbSource = loadFromDb();
        fetchFromNetwork(dbSource);
//        result.addSource(dbSource, new Observer<ResultType>() {
//            @Override
//            public void onChanged(@Nullable ResultType newData) {
//                result.removeSource(dbSource);
//                if (shouldFetch(newData)) {
//                    fetchFromNetwork(dbSource);
//                } else {
//                    result.addSource(dbSource, new Observer<ResultType>() {
//                        @Override
//                        public void onChanged(@Nullable ResultType newData) {
//                            result.setValue(Resource.success(newData));
//                        }
//                    });
//                }
//            }
//        });
    }

    private void fetchFromNetwork(final Observable<ResultType> dbSource) {

        final Observable<ApiResponse<ResultType>> apiResponse = createCall();
        apiResponse.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ErrorHandleSubscriber<ApiResponse<ResultType>>(mRxErrorHandler) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(final ApiResponse<ResultType> response) {
                        final LiveData<ResultType> resultTypeLiveData = new LiveData<ResultType>() {
                            @Override
                            protected void onActive() {
                                super.onActive();
                                setValue(processResponse(response));
                            }
                        };
                        if (response.isSuccessful()) {
                            mAppExecutors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    saveCallResult(processResponse(response));
                                    mAppExecutors.mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.addSource(resultTypeLiveData, new Observer<ResultType>() {
                                                @Override
                                                public void onChanged(@Nullable ResultType newData) {
                                                    result.setValue(Resource.success(newData));
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        } else {
                            onFetchFailed();
                            mAppExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    result.addSource(resultTypeLiveData, new Observer<ResultType>() {
                                        @Override
                                        public void onChanged(@Nullable ResultType newData) {
                                            result.setValue(Resource.error(response.errorMessage, newData));
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
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
    protected boolean shouldFetch(@Nullable ResultType data) {
        return true;
    }

    /**
     * 从数据库获取数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract Observable<ResultType> loadFromDb();

    /**
     * 调取api获取数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<ResultType>> createCall();

}
