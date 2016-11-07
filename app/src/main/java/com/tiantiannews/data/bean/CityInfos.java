package com.tiantiannews.data.bean;


import com.tiantiannews.base.adapter.recycleview.entity.MultiItemEntity;

public class CityInfos implements MultiItemEntity {

    public static final int LOCATION = 1;
    public static final int CITIES = 2;

    private int itemType;

    public CityInfos(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
