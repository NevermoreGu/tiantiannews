package com.tiantiannews.base;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.litepal.LitePalApplication;

public class BaseApplication extends LitePalApplication {

    public static BaseApplication instance;

    private RefWatcher refWatcher;

    public static boolean CheckWifi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);
        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {
                MemorySizeCalculator calculator = new MemorySizeCalculator(instance);
                int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
                int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
                Glide.get(instance).clearMemory();
                System.gc();
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {

            }
        });
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

}

