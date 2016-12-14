package com.tiantiannews.di.component;

import com.tiantiannews.data.source.TasksRepository;
import com.tiantiannews.di.module.TasksRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TasksRepositoryModule.class, dependencies = AppComponent.class)
public interface TasksRepositoryComponent {

    TasksRepository getTasksRepository();
}
