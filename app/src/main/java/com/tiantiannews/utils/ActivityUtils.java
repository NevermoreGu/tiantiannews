package com.tiantiannews.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityUtils {

    //通过包名类名启动activity
    public static void openActivity(Activity activity, Class<?> activityClass) {
        openActivity(activity, activityClass, null);
    }

    public static void openActivity(Activity activity, Class<?> activityClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public static void openActivityForResult(Activity activity, Class<?> activityClass, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        activity.startActivityForResult(intent, requestCode);
    }

    //通过action启动activity
    public static void openActivity(Activity activity, String action) {
        openActivity(activity, action, null);
    }

    public static void openActivity(Activity activity, String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public static void openActivityForResult(Activity activity, String action, int requestCode) {
        Intent intent = new Intent(action);
        activity.startActivityForResult(intent, requestCode);
    }

}
