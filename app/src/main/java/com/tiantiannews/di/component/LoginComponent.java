package com.tiantiannews.di.component;


import com.tiantiannews.di.module.LoginPresenterModule;
import com.tiantiannews.di.scope.FragmentScoped;
import com.tiantiannews.ui.activity.LoginActivity;

import dagger.Component;

@FragmentScoped
@Component(dependencies = LoginRepositoryComponent.class, modules = LoginPresenterModule.class)
public interface LoginComponent {
	
    void inject(LoginActivity activity);
}
