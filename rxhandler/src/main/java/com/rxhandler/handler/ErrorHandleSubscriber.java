package com.rxhandler.handler;

import com.rxhandler.core.RxErrorHandler;

import rx.Subscriber;


public abstract class ErrorHandleSubscriber<T> extends Subscriber<T> {
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

