package com.tiantiannews.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.tiantiannews.DaysApplication;
import com.tiantiannews.R;
import com.tiantiannews.di.component.AppComponent;
import com.tiantiannews.ui.interf.BaseViewInterface;
import com.tiantiannews.ui.widget.AppBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterface {

    protected final String TAG = this.getClass().getSimpleName();

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

    protected FragmentManager mFragmentManager;

    protected void initAppBar() {
        appBar = (AppBar) findViewById(R.id.app_bar);
    }

    protected DaysApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        mApplication = (DaysApplication)getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        mContext = this;
        mInflater = getLayoutInflater();
        handlerIntent();
        initVariables();
        initViews();
        loadData();
    }

    @Override
    public void initVariables() {
        mFragmentManager = getSupportFragmentManager();
    }

    protected void setupActivityComponent(AppComponent appComponent){

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

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }
}
