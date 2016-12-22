package com.tiantiannews.utils.Rx;

import rx.Subscriber;


public abstract class ErrorHandleSubscriber<T> extends Subscriber<T> {


    public ErrorHandleSubscriber() {
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        ErrorHandler.handleError(e);
    }

}

