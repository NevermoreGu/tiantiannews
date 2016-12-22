package com.tiantiannews.utils.net;

import com.network.Exception.NetException;

public abstract class NetCallBack<T> {

    public void onStart() {}

    public abstract void onResponse(T response);

    public abstract void onErrorResponse(NetException netException);

    public void onFinish() {}
}
