package com.network.di.module;

import com.network.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by guqian on 2017/7/12.
 */

@Module
public class ExecutorsModule {

    @Singleton
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }
}
