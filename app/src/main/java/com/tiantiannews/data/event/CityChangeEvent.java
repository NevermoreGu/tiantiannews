package com.tiantiannews.data.event;

import com.amap.api.location.AMapLocation;

public class CityChangeEvent {

    private AMapLocation aMapLocation;

    public CityChangeEvent(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
    }

    public AMapLocation getCity() {
        return aMapLocation;
    }

    public void setCity(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
    }
}
