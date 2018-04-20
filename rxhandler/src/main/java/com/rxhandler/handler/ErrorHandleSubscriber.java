package com.rxhandler.handler;

import com.rxhandler.core.RxErrorHandler;

import io.reactivex.Observer;


public abstract class ErrorHandleSubscriber<T> implements Observer<T> {

    private ErrorHandlerFactory mHandlerFactory;

    public ErrorHandleSubscriber(RxErrorHandler rxErrorHandler){
        this.mHandlerFactory = rxErrorHandler.getHandlerFactory();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mHandlerFactory.handleError(e);
    }

}

