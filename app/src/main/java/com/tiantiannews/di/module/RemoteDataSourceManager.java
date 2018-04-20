package com.tiantiannews.di.module;

import com.tiantiannews.api.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteDataSourceManager {

    private ApiService mApiService;

    @Inject
    public RemoteDataSourceManager(ApiService apiService){
        this.mApiService = apiService;
    }

    public ApiService   getApiService() {
        return mApiService;
    }
}
