package com.tiantiannews.utils.cache;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by guqian on 2017/4/26.
 */

public interface CacheProvider {

    void save(Context context, Serializable ser, String key);

    Serializable read(Context context, String key);

    String hashKeyForDisk(String key);

    int getMaxSize();
}
