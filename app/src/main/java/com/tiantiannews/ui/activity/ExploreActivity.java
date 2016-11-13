package com.tiantiannews.ui.activity;

import android.content.Intent;

import com.tiantiannews.base.BaseActivity;

import java.util.ArrayList;

public class ExploreActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }

    //本地相册选择
    private void openCameraPhotoPick(ArrayList<String> list) {
        Intent intent = new Intent();
//        intent.setClass(this, SelectPicturesActivity.class);
//        Bundle b = new Bundle();
//
//        b.putSerializable(SelectPicturesInfo.EXTRA_PARAMETER, mSelectPicturesInfo);
//        intent.putExtras(b);
//        startActivityForResult(intent, SelectPicturesInfo.TAKE_PICTURE_FROM_GALLERY);

    }
}
