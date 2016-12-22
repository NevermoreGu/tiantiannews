package com.rxhandler.core;

import android.content.Context;

import com.rxhandler.handler.ErrorHandlerFactory;
import com.rxhandler.handler.listener.ResponseErrorListener;


public class RxErrorHandler {

    public final String TAG = this.getClass().getSimpleName();

    private ErrorHandlerFactory mHandlerFactory;

    private RxErrorHandler(Builder builder) {
        this.mHandlerFactory = builder.errorHandlerFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ErrorHandlerFactory getHandlerFactory() {
        return mHandlerFactory;
    }

    public static final class Builder {
        private Context context;
        private ResponseErrorListener responseErrorListener;
        private ErrorHandlerFactory errorHandlerFactory;

        private Builder() {
        }

        public Builder with(Context context) {
            this.context = context;
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener responseErroListener) {
            this.responseErrorListener = responseErroListener;
            return this;
        }

        public RxErrorHandler build() {
            if (context == null)
                throw new IllegalStateException("context is required");

            if (responseErrorListener == null)
                throw new IllegalStateException("responseErroListener is required");

            this.errorHandlerFactory = new ErrorHandlerFactory(context, responseErrorListener);

            return new RxErrorHandler(this);
        }
    }


}
