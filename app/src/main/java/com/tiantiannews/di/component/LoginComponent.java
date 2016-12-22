package com.tiantiannews.di.component;


import com.tiantiannews.data.source.LoginRepository;
import com.tiantiannews.di.module.LoginPresenterModule;
import com.tiantiannews.di.module.LoginRepositoryModule;
import com.tiantiannews.di.scope.ActivityScope;
import com.tiantiannews.ui.activity.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {LoginPresenterModule.class, LoginRepositoryModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);

    LoginRepository getLoginRepository();
}
