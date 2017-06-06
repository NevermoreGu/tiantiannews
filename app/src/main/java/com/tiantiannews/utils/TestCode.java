package com.tiantiannews.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * Created by guqian on 2017/4/27.
 * 写一些测试的代码
 */

public class TestCode {

    protected static final String TAG = "TestCode";

    /**
     *
     * 04-27 14:04:51.614 418-418/com.tiantiannews D/TestCode: getFilesDir = /data/data/com.tiantiannews/files
     04-27 14:04:51.614 418-418/com.tiantiannews D/TestCode: getCacheDir = /data/data/com.tiantiannews/cache
     04-27 14:04:51.616 418-418/com.tiantiannews D/TestCode: getExternalStoragePublicDirectory = /storage/emulated/0/Pictures
     04-27 14:04:51.617 418-418/com.tiantiannews D/TestCode: getDataDirectory = /data
     04-27 14:04:51.617 418-418/com.tiantiannews D/TestCode: getExternalStorageDirectory = /storage/emulated/0
     04-27 14:04:51.617 418-418/com.tiantiannews D/TestCode: getDownloadCacheDirectory = /cache
     04-27 14:04:51.621 418-418/com.tiantiannews D/TestCode: getExternalCacheDir = /storage/emulated/0/Android/data/com.tiantiannews/cache
     04-27 14:04:51.622 418-418/com.tiantiannews D/TestCode: getExternalFilesDir = /storage/emulated/0/Android/data/com.tiantiannews/files/Pictures

     * @param context
     */
    public static void testGetApiFile(Context context) {
        Log.d(TAG, "getFilesDir = " + context.getFilesDir().getAbsolutePath());
        Log.d(TAG, "getCacheDir = " + context.getCacheDir().getAbsolutePath());
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "getExternalStoragePublicDirectory = " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            Log.d(TAG, "getDataDirectory = " + Environment.getDataDirectory().getAbsolutePath());
            Log.d(TAG, "getExternalStorageDirectory = " + Environment.getExternalStorageDirectory().getAbsolutePath());
            Log.d(TAG, "getDownloadCacheDirectory = " + Environment.getDownloadCacheDirectory().getAbsolutePath());
            Log.d(TAG, "getExternalCacheDir = " + context.getExternalCacheDir().getAbsolutePath());
            Log.d(TAG, "getExternalFilesDir = " + context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        }
    }
}
