package com.tiantiannews.aidl;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageInfo implements Parcelable {

    public String path;
    public String name;
    public long time;
    public String source_image; //源图
    public boolean isAddButton = false; //是否是添加按钮
    public Bitmap bitmap;

    public ImageInfo() {
    }

    public ImageInfo(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSource_image() {
        return source_image;
    }

    public void setSource_image(String source_image) {
        this.source_image = source_image;
    }

    public boolean isAddButton() {
        return isAddButton;
    }

    public void setIsAddButton(boolean isAddButton) {
        this.isAddButton = isAddButton;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean equals(Object o) {
        try {
            ImageInfo other = (ImageInfo) o;
            return this.path.equalsIgnoreCase(other.path);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(o);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeLong(this.time);
        dest.writeString(this.source_image);
        dest.writeByte(isAddButton ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.bitmap, 0);
    }

    protected ImageInfo(Parcel in) {
        this.path = in.readString();
        this.name = in.readString();
        this.time = in.readLong();
        this.source_image = in.readString();
        this.isAddButton = in.readByte() != 0;
        this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };
}
