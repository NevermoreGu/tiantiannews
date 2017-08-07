package com.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guqian on 2017/8/7.
 */

public class ResponseResult<T> {

    @SerializedName("detail")
    public T detail;

    public String cmd;
    public int result;
    public String resultNote;
    public int pages;
    public int pageNo;
}
