package com.tiantiannews.utils.image;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tiantiannews.base.BaseApplication;
import com.tiantiannews.utils.TDevice;

import java.io.IOException;
import java.io.InputStream;

public class GlideImageLoaderProvider implements BaseImageLoaderProvider {

    @Override
    public void loadImage(Context ctx, ImageLoader img) {

        /**
         * 检查 wifi下下载图片是否开启，如果开启检查是否wifi状态下
         */
        boolean flag;
        if (BaseApplication.CheckWifi) {
            if (TDevice.isWifiOpen()) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = true;
        }
        if (flag) {
            loadNormal(ctx, img);
        } else {
            loadCache(ctx, img);
        }
    }


    /**
     * load image with Glide
     */
    private void loadNormal(Context ctx, ImageLoader img) {
//        Glide.with(ctx).load(img.url()).placeholder(img.placeHolder()).into(img.imgView());
        Glide.with(ctx).load(img.url()).asBitmap().placeholder(img.placeHolder()).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                if (delegate != null) {
//                    delegate.onSuccess(imageView, finalPath);
//                }
                return false;
            }
        }).into(img.imgView());
    }


    /**
     * load cache image with Glide
     */
    private void loadCache(Context ctx, ImageLoader img) {
        Glide.with(ctx).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(img.url()).placeholder(img.placeHolder()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img.imgView());
    }
}
