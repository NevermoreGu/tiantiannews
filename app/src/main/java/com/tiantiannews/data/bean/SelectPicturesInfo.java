package com.tiantiannews.data.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectPicturesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String EXTRA_PARAMETER = "extra_select_pictures_parameter";

    //public static final String EXTRA_IMAGES_LIST = "selected_image_list";		//已经选择的图片集
    public static final int TAKE_PICTURE_FROM_CAMERA = 100;
    public static final int TAKE_PICTURE_FROM_GALLERY = 200;
    public static final int TAKE_PICTURE_PREVIEW = 300;

    private boolean show_camera = true;        //是否显示相机，默认显示
    private List<String> image_list;        //已经选择的图片集

    public boolean isShow_camera() {
        return show_camera;
    }

    public void setShow_camera(boolean show_camera) {
        this.show_camera = show_camera;
    }

    public List<String> getImage_list() {
        if (image_list == null) {
            image_list = new ArrayList<>();
        }
        return image_list;
    }

    public void setImage_list(List<String> image_list) {
        this.image_list = image_list;
    }


}
