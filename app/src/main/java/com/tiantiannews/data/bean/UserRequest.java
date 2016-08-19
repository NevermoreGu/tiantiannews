package com.tiantiannews.data.bean;

public class UserRequest {

    public String cmd;
    public ParamsBean params;

    public static class ParamsBean {
        public String userName;
        public String password;
        public String ua;
        public String sdk;
        public String imei;
        public String imsi;
        public String appVersion;
        public String mapType;
    }

}
