package com.utils;

import android.util.Log;

import timber.log.Timber;


public class LogUtils {

    public static boolean DEBUG = true;

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Timber.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Timber.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Timber.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Timber.e(tag, msg);
        }
    }

}
