package com.tiantiannews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.data.bean.ImageInfo;

import java.util.ArrayList;

import butterknife.BindView;

public class PicturesBrowseActivity extends BaseActivity {

    @BindView(R.id.vp_picture_browse)
    ViewPager vpBrowse;

    private int position = 0;
    private ArrayList<ImageInfo> imageInfos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_browse;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
//        vpBrowse.setAdapter(new PictureBrowsePagerAdapter(imageInfos, this));
//        vpBrowse.startAutoScroll(0);
//        vpBrowse.setCurrentItem(position - 1);
    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        imageInfos = bundle.getParcelableArrayList("pictures");
    }
}