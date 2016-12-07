package com.network.Exception;


import java.lang.Exception;


public class Throwable extends Exception {

    private int code;
    private String message;

    public Throwable(java.lang.Throwable throwable, int code) {
       this(throwable, code, "网络错误");
    }

    public Throwable(java.lang.Throwable throwable, int code, String msg) {
        super(throwable);
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
