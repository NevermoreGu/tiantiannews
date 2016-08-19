package com.tiantiannews.api;

import com.tiantiannews.data.bean.UserRequest;

public class ApiParams {

    public static UserRequest getLoginParams(String name, String pass) {

        UserRequest userRequest = new UserRequest();
        userRequest.cmd = "signin";
        UserRequest.ParamsBean paramsBean = new UserRequest.ParamsBean();
        paramsBean.appVersion = "1.0.3";
        paramsBean.imei = "860887038344759";
        paramsBean.imsi = "460018051209707";
        paramsBean.mapType = "google";
        paramsBean.sdk = "21";
        paramsBean.ua = "Redmi Note 3";
        paramsBean.sdk = "1.0.3";
        paramsBean.userName = name;
        paramsBean.password = pass;
        userRequest.params = paramsBean;
        return userRequest;
    }
}
