package com.tiantiannews.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.letter);
        dest.writeString(this.rank);
    }

    protected City(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.letter = in.readString();
        this.rank = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
