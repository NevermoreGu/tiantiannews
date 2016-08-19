package com.tiantiannews.utils.net;


import com.tiantiannews.utils.StringUtils;

public class NetBuilder {

    public String url;
    public String param;
    public NetCallBack callBack;

    public NetBuilder(Builder builder) {
        this.url = builder.url;
        this.param = builder.param;
        this.callBack = builder.callBack;
    }

    public static class Builder {
        private String url;
        private String param;
        private NetCallBack callBack;

        public Builder url(String url) {
            if (url == null) throw new IllegalArgumentException("url == null");
            this.url = url;
            return this;
        }

        public Builder param(String param) {
            this.param = param;
            return this;
        }

        public Builder callback(NetCallBack callBack) {
            if (StringUtils.isEmpty(url)) {
                this.url = "";
            }
            this.callBack = callBack;
            return this;
        }

        public NetBuilder build() {
            return new NetBuilder(this);
        }
    }
}
