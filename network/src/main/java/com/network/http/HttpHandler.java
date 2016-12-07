package com.network.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public interface HttpHandler {

    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);

    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);
}
