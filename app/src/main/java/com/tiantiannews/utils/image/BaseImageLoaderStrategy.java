package com.tiantiannews.utils.image;

import android.content.Context;

/**
 * Created by jess on 8/5/16 15:50
 * contact with jess.yan.effort@gmail.com
 */
public interface BaseImageLoaderStrategy<T extends ImageLoader> {
    void loadImage(Context ctx, T config);
}
