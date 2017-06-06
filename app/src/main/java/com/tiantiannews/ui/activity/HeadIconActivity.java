package com.tiantiannews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.utils.image.ImageLoader;
import com.tiantiannews.utils.image.ImageLoaderUtil;

import butterknife.BindView;

public class HeadIconActivity extends BaseActivity {

    @BindView(R.id.img_head_icon)
    ImageView mImgHeadIcon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_icon;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String path = bundle.getString("path");
        ImageLoaderUtil imageLoaderUtil = ImageLoaderUtil.getInstance();
        ImageLoader imageLoader = ImageLoader.builder().url(path).imgView(mImgHeadIcon).placeHolder(R.drawable.default_img_bg).type(1).build();
        imageLoaderUtil.loadImage(mContext, imageLoader);

    }
}
