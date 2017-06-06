package com.tiantiannews.utils.cache;

import android.support.annotation.Nullable;

/**
 * Created by guqian on 2017/4/29.
 */

public interface DiskCache {

    interface Factory {

        /** 250 MB of cache. */
        int DEFAULT_DISK_CACHE_SIZE = 250 * 1024 * 1024;
        String DEFAULT_DISK_CACHE_DIR = "file_manager_disk_cache";

        /**
         * Returns a new disk cache, or {@code null} if no disk cache could be created.
         */
        @Nullable
        DiskCache build();
    }
}
