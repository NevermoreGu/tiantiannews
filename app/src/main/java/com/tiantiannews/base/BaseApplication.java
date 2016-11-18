package com.tiantiannews.base;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tiantiannews.BuildConfig;
import com.tiantiannews.di.component.AppComponent;
import com.tiantiannews.di.component.DaggerAppComponent;
import com.tiantiannews.di.module.AppModule;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    private RefWatcher mRefWatcher;

    public static boolean CheckWifi;

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

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
        installLeakCanary();
    }


    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * leakCanary内存泄露检查
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

}

