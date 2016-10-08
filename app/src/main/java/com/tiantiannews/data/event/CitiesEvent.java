package com.tiantiannews.data.event;

import com.tiantiannews.data.bean.City;

import java.util.List;

public class CitiesEvent {

    private List<City> cities;

    public CitiesEvent(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
