package com.tiantiannews.utils.image;

import android.widget.ImageView;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ImageLoader {

    abstract int type();
    abstract int placeHolder();
    abstract String url();
    abstract ImageView imgView();

    public static Builder builder() {
        return new AutoValue_ImageLoader.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder type(int value);
        public abstract Builder placeHolder(int value);
        public abstract Builder url(String value);
        public abstract Builder imgView(ImageView value);

        public abstract ImageLoader build();
    }

//    private int type;  //类型 (大图，中图，小图)
//    private String url; //需要解析的url
//    private int placeHolder; //当没有成功加载的时候显示的图片
//    private ImageView imgView; //ImageView的实例
//    private int wifiStrategy;//加载策略，是否在wifi下才加载
//
//    private ImageLoader(Builder builder) {
//        this.type = builder.type;
//        this.url = builder.url;
//        this.placeHolder = builder.placeHolder;
//        this.imgView = builder.imgView;
//        this.wifiStrategy = builder.wifiStrategy;
//    }
//    public int getType() {
//        return type;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public int getPlaceHolder() {
//        return placeHolder;
//    }
//
//    public ImageView getImgView() {
//        return imgView;
//    }
//
//    public int getWifiStrategy() {
//        return wifiStrategy;
//    }
//
//    public static class Builder {
//
//        private int type;
//        private String url;
//        private int placeHolder;
//        private ImageView imgView;
//        private int wifiStrategy;
//
//        public Builder type(int type) {
//            this.type = type;
//            return this;
//        }
//
//        public Builder url(String url) {
//            this.url = url;
//            return this;
//        }
//
//        public Builder placeHolder(int placeHolder) {
//            this.placeHolder = placeHolder;
//            return this;
//        }
//
//        public Builder imgView(ImageView imgView) {
//            this.imgView = imgView;
//            return this;
//        }
//
//        public Builder strategy(int strategy) {
//            this.wifiStrategy = strategy;
//            return this;
//        }
//
//        public ImageLoader build() {
//            return new ImageLoader(this);
//        }
//
//    }


}
