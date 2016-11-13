package com.tiantiannews.ui.adapter;

import com.tiantiannews.R;
import com.tiantiannews.base.adapter.recycleview.BaseMultiItemQuickAdapter;
import com.tiantiannews.base.adapter.recycleview.BaseViewHolder;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.data.bean.CityInfos;

import java.util.ArrayList;
import java.util.List;

public class CitiesMultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<CityInfos, BaseViewHolder> {

    public CitiesMultipleItemQuickAdapter(List<CityInfos> data) {
        super(data);
        addItemType(CityInfos.LOCATION, R.layout.item_city_list_head);
        addItemType(CityInfos.CITIES, R.layout.item_all_city);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityInfos cityInfos) {
        switch (helper.getItemViewType()) {
            case CityInfos.LOCATION:
                City city = new City(1, "南京", "nanjing");
                List<City> location = new ArrayList<>();
                location.add(city);
                helper.setAdapter(R.id.gv_item_city_list_location,new LocationCityAdapter(mContext, location));
                List<City> cities = new ArrayList<City>();
                cities.add(new City("北京"));
                cities.add(new City("上海"));
                cities.add(new City("广州"));
                cities.add(new City("深圳"));
                cities.add(new City("杭州"));
                cities.add(new City("南京"));
                cities.add(new City("天津"));
                cities.add(new City("武汉"));
                cities.add(new City("重庆"));
                helper.setAdapter(R.id.gv_item_city_list_hot,new LocationCityAdapter(mContext, cities));

                break;
            case CityInfos.CITIES:



                break;
        }

    }
}
