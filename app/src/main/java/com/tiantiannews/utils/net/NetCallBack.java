package com.tiantiannews.utils.net;

import com.network.Exception.NetworkException;

public abstract class NetCallBack<T> {

    public void onStart() {}

    public abstract void onResponse(T response);

    public abstract void onErrorResponse(NetworkException netException);

    public void onFinish() {}
}
