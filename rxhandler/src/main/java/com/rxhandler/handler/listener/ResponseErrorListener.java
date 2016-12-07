package com.rxhandler.handler.listener;

import android.content.Context;

public interface ResponseErrorListener {
    void handleResponseError(Context context, Exception e);
}
