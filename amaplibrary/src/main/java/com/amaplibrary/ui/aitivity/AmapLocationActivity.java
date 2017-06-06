package com.amaplibrary.ui.aitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amaplibrary.ui.interf.BaseViewInterface;

abstract class AMapLocationActivity extends AppCompatActivity implements BaseViewInterface {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption aMapLocationClientOption;

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this);
        //设置定位参数
        aMapLocationClientOption = getDefaultOption();
        locationClient.setLocationOption(aMapLocationClientOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initVariables() {
        //初始化定位
        initLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startLocation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocation();
    }

    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
//        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        return mOption;
    }

    public void setAMapLocationOption(AMapLocationClientOption option) {
        this.aMapLocationClientOption = option;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc && loc.getErrorCode() == 0) {
                //定位成功,解析定位结果
                onLocationSuccess(loc);
            } else {
                //定位失败
                onLocationFail();
            }
        }
    };

    /**
     * 开始定位
     */
    public void startLocation() {
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            aMapLocationClientOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    public abstract void onLocationSuccess(AMapLocation loc);

    public abstract void onLocationFail();
}
