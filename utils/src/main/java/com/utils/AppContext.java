package com.utils;

import android.content.Context;

public class AppContext {

    private static Context mContext;

    public void initAppContext(Context context) {
        this.mContext = context;
    }

    public static Context getAppContext() {
        if (mContext == null) {

        }
        return mContext;
    }
}
