package com.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.utils.FileType.IMG;
import static com.utils.FileType.VID;

/**
 * Created by guqian on 2017/5/23.
 */


@IntDef({IMG, VID})
@Retention(RetentionPolicy.SOURCE)
public @interface FileType {

    int IMG = 0;
    int VID = 1;
}
