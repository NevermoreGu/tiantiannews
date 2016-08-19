package com.tiantiannews.base;

import com.google.gson.annotations.SerializedName;

public class BaseModel<T> {

    @SerializedName("detail")
    public T detail;

    public String cmd;
    public int result;
    public String resultNote;
    public int pages;
    public int pageNo;
}
