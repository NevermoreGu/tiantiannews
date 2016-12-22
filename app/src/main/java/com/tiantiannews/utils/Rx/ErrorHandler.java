package com.tiantiannews.utils.Rx;

import com.network.Exception.NetException;
import com.network.Exception.Throwable;

public class ErrorHandler {


    /**
     *  处理错误
     * @param throwable
     */
    public static void handleError(java.lang.Throwable throwable) {
       Throwable exception  = NetException.handleException(throwable);

    }
}
