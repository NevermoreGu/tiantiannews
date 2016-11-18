package com.tiantiannews.di.component;

import android.app.Application;

import com.tiantiannews.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application getApplication();
}
