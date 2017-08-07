package com.utils.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import static com.utils.FileUtils.getDiskCachePicture;


public class CameraUtils {

    public static final int RC_CAMERA_PERM = 123;
    public static final int TAKE_PICTURE_FROM_CAMERA = 100;
    public static final int TAKE_PICTURE_FROM_GALLERY = 200;
    public static final int TAKE_PICTURE_PREVIEW = 300;

    public static File showCameraAction(Activity context) {
        File file = getDiskCachePicture(context);
        showCameraAction(context, file);
        return file;
    }

    public static File showCameraAction(Activity context, String temporaryFileName) {
        File file = getDiskCachePicture(context, temporaryFileName);
        showCameraAction(context, file);
        return file;
    }

    /**
     * 在onActivityResult中 可以通过key得到一个返回值，即 Bitmap imageBitmap = (Bitmap) extras.get("data");此返回值是
     * 拍摄照片的缩略图，但是一般不这样做，我们需要的是全尺寸的照片，所以我们通过filename保存拍摄的照片
     * @param context
     * @param temporaryFile
     * @return
     */
    public static File showCameraAction(Activity context, File temporaryFile) {
        // 跳转到系统照相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temporaryFile));
            context.startActivityForResult(intent, TAKE_PICTURE_FROM_CAMERA);
        } else {
            //相机出现异常
        }
        return temporaryFile;
    }
}
