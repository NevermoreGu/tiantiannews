package com.tiantiannews.utils.net;

public abstract class NetCallBack<T> {

    public void onStart() {}

    public abstract void onResponse(T response);

    public abstract void onErrorResponse(String error);

    public void onFinish() {}
}
