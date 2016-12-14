package com.tiantiannews.di.component;


import com.tiantiannews.di.module.LoginPresenterModule;
import com.tiantiannews.di.scope.FragmentScoped;
import com.tiantiannews.ui.activity.LoginActivity;

import dagger.Component;

@FragmentScoped
@Component(dependencies = TasksRepositoryComponent.class, modules = LoginPresenterModule.class)
public interface TasksComponent {
	
    void inject(LoginActivity activity);
}
