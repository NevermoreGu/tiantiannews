package com.tiantiannews.data.bean;

import java.io.Serializable;

public class City implements Serializable {

    public int id;
    public String name;
    public String letter;
    public String rank;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(int id, String name, String letter) {
        this.id = id;
        this.name = name;
        this.letter = letter;
    }


    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(letter);
    }

    *//**
     * 该静态常量值负责恢复从Parcel数据包中恢复City对象
     *//*
    public static final Creator<City> CREATOR = new Creator<City>() {

        @Override
        public City createFromParcel(Parcel source) {
            City city = new City();
            city.id = source.readInt();
            city.name = source.readString();
            city.letter = source.readString();
            return city;
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };*/
}
