package com.tiantiannews.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.tiantiannews.aidl.IImageAidlInterface;
import com.tiantiannews.data.bean.SelectPicturesInfo;

import java.util.ArrayList;
import java.util.List;

public class ImageService extends Service {

    protected final String TAG = this.getClass().getSimpleName();

    private List<String> mImageInfo = new ArrayList<>();

    public ImageService() {
    }

    IImageAidlInterface.Stub mBinder = new IImageAidlInterface.Stub() {
        @Override
        public List<String> getImages() throws RemoteException {
            return mImageInfo;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        SelectPicturesInfo mSelectPicturesInfo = (SelectPicturesInfo) intent.getSerializableExtra(SelectPicturesInfo.EXTRA_PARAMETER);
        if (mSelectPicturesInfo != null && mSelectPicturesInfo.getImage_list() != null) {
            mImageInfo.clear();
            mImageInfo.addAll(mSelectPicturesInfo.getImage_list());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
