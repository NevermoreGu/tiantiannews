package com.base.utils.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * Created by guqian on 2017/3/30.
 * 相机类
 */

public class Camera {

    public static void showCameraAction(Activity activity) {
        // 跳转到系统照相机
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
//            // 设置系统相机拍照后的输出路径
//            // 创建临时文件
//            mTmpFile = FileUtils.createTmpFile(this);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
//            activity.startActivityForResult(cameraIntent, 100);
//        } else {
////            Toast.makeText(this, R.string.select_picture_msg_no_camera, Toast.LENGTH_SHORT).show();
//        }
    }

}
