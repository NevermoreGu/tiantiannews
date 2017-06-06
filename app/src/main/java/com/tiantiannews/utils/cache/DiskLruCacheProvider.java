package com.tiantiannews.utils.cache;

import android.content.Context;

import com.jakewharton.disklrucache.DiskLruCache;
import com.utils.FileUtils;
import com.utils.TDevice;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Created by guqian on 2017/4/27.
 */

public class DiskLruCacheProvider implements CacheProvider {

    private static int valueCount = 1;// 同一个key可以对应多少个缓存文件
    private static int defaultIndex = 0;
    public static final String CACHE_OBJECT = "object";// 对象缓存目录

    @Override
    public void save(Context context, Serializable ser, String key) {
        ObjectOutputStream oos = null;
        try {
            DiskLruCache.Editor editor = getDiskLruCacheOutputStream(context,
                    CACHE_OBJECT, key);
            if (editor != null) {
                oos = new ObjectOutputStream(editor.newOutputStream(defaultIndex));
                oos.writeObject(ser);
                oos.flush();
                editor.commit();
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
//            StreamUtil.close(oos);
        }

    }

    @Override
    public Serializable read(Context context, String key) {
        return null;
    }

    @Override
    public String hashKeyForDisk(String key) {
        return FileUtils.hashKeyForDisk(key);
    }

    @Override
    public int getMaxSize() {
        return 10 * 1024 * 1024;
    }

    private DiskLruCache.Editor getDiskLruCacheOutputStream(Context context, String uniqueName, String key) {
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache mDiskLruCache = DiskLruCache.open(
                    FileUtils.getDiskCacheDir(context, uniqueName), TDevice.getVersionCode(context), valueCount,
                    getMaxSize());
            editor = mDiskLruCache.edit(hashKeyForDisk(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return editor;
    }
}
