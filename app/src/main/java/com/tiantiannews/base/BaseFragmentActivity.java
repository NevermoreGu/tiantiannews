package com.tiantiannews.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.base.ui.interf.BaseViewInterface;
import com.base.ui.widget.AppBar;
import com.tiantiannews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseFragmentActivity extends FragmentActivity implements BaseViewInterface {

    protected AppBar appBar;

    protected LayoutInflater mInflater;

    protected void onBeforeSetContentLayout() {
    }

    protected abstract int getLayoutId();

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    /**
     * 处理从上一个界面接收的intent,或singleTop,singleTask,singleInstance onNewIntent中intent
     */
    protected void handlerIntent() {
    }

    protected List<ImageView> imageViews = new ArrayList<>();

    protected Activity mContext;

    protected void initAppBar() {
        appBar = (AppBar) findViewById(R.id.app_bar);
    }

    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        mContext = this;
        mInflater = getLayoutInflater();
        handlerIntent();
        initVariables();
        initViews();
        loadData();
    }

    @Override
    public void initVariables() {
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /**
         * 必须设置setIntent
         */
        setIntent(intent);
        handlerIntent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseImageView();
    }

    /**
     * 将imageView添加到imageView集合中
     *
     * @param imageView
     */
    protected void addImageView(ImageView imageView) {
        if (imageView != null) {
            imageViews.add(imageView);
        }
    }

    /**
     * 将imageView集合中imageView资源回收，手动消除内存
     */
    private void releaseImageView() {
        if (imageViews != null && imageViews.size() > 0) {
            for (ImageView imageView : imageViews) {
                Drawable d = imageView.getDrawable();
                if (d != null) {
                    d.setCallback(null);
                }
                imageView.setImageDrawable(null);
                imageView.setBackgroundDrawable(null);
            }
        }
        imageViews.clear();
        imageViews = null;
    }
}
