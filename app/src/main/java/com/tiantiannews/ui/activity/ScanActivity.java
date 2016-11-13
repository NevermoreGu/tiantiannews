package com.tiantiannews.ui.activity;

import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.qrcode.QRCodeView;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.ui.widget.ZXingView;
import com.tiantiannews.utils.ActivityUtils;
import com.tiantiannews.utils.ViewUtils;

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    private QRCodeView mQRCodeView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initAppBar() {
        super.initAppBar();
        appBar.setAppBarTitleAndColor(getString(R.string.scan), R.color.white);
        appBar.setAppBarLeftDrawable(R.drawable.ic_title_back_white_normal);
        ViewUtils.addTouchImageResource(appBar.getAppBarImageLeft(), R.drawable.ic_title_back_white_press, R.drawable.ic_title_back_white_normal);
        appBar.setAppBarRightTextAndColor(getString(R.string.select_picture_album_button), R.color.whit_secondary);
        appBar.setOnClickListenerAppBarRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivityForResult(mContext, SelectPicturesActivity.class, 0);
            }
        });
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        initAppBar();
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.startSpot();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);
//
//            /*
//            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
//            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
//             */
//            new AsyncTask<Void, Void, String>() {
//                @Override
//                protected String doInBackground(Void... params) {
//                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
//                }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }.execute();
//        }
    }
}