package com.rxhandler.handler;

import android.content.Context;

import com.rxhandler.handler.listener.ResponseErrorListener;

public class ErrorHandlerFactory {

    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
        this.mContext = mContext;
    }

    /**
     *  处理错误
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErrorListener.handleResponseError(mContext, (Exception) throwable);

    }
}
