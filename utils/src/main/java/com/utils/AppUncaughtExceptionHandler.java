package com.utils;

import android.content.Context;

/**
 * @author guqian
 * @date 2018/3/9
 */

public class AppUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static AppUncaughtExceptionHandler sInstance = new AppUncaughtExceptionHandler();

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    //这里主要完成初始化工作
    public void init(Context context) {
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
    }

    public static AppUncaughtExceptionHandler getInstance() {
        return sInstance;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
//        if (mDefaultCrashHandler != null) {
//            mDefaultCrashHandler.uncaughtException(t, e);
//        } else {
//
//        }
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.tiantiannews"));
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());//再此之前可以做些退出等操作
    }
}
