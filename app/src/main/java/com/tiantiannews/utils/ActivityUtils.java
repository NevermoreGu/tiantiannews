package com.tiantiannews.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tiantiannews.ui.widget.StatusBarView;

public class ActivityUtils {

    /**
     * 通过包名类名启动activity
     *
     * @param activity
     * @param activityClass
     */
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

    /**
     * 通过action启动activity
     *
     * @param activity
     * @param action
     */
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

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             int frameId, @NonNull Fragment... fragments) {
        for (Fragment fragment : fragments) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
        }
    }

    //设置的全屏模式
    public static  void setFullSreen(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置透明状态栏,这样才能让 ContentView 向上
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     *设置彩色的状态栏
     *
     * @param activity
     * @param color 状态栏需要设置的背景颜色
     * @param statusBarAlpha 状态栏需要设置的背景颜色的透明度
     */
    public static void setColor(Activity activity, @ColorInt int color, int statusBarAlpha){
        //先设置的全屏模式
        setFullSreen(activity);
        //在透明状态栏的垂直下方放置一个和状态栏同样高宽的view
        addStatusBarBehind(activity,color,statusBarAlpha);
    }
    /**
     * 添加了一个状态栏(实际上是个view)，放在了状态栏的垂直下方
     */
    public static void addStatusBarBehind(Activity activity, @ColorInt int color, int statusBarAlpha) {
        //获取windowphone下的decorView
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        int       count     = decorView.getChildCount();
        //判断是否已经添加了statusBarView
        if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
            decorView.getChildAt(count - 1).setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
        } else {
            //新建一个和状态栏高宽的view
            StatusBarView statusView = createStatusBarView(activity, color, statusBarAlpha);
            decorView.addView(statusView);
        }
        setRootView(activity);
    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        //rootview不会为状态栏流出状态栏空间
        ViewCompat.setFitsSystemWindows(rootView,false);
        rootView.setClipToPadding(true);
    }

    private static StatusBarView createStatusBarView(Activity activity, int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
        return statusBarView;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
}
