package com.tiantiannews.utils.image;

import android.content.Context;

public interface BaseImageLoaderProvider {

    void loadImage(Context ctx, ImageLoader img);

    void clearMemoryCache();
}
